import Foundation
import WatchConnectivity

final class WatchSession: NSObject, ObservableObject, WCSessionDelegate {
    @Published var brief = WristBrief(
        displayName: "",
        statusText: "Waiting for iPhone",
        nextAppointment: nil,
        alertCount: 0,
        updatedAt: .distantPast,
        isSensitiveHidden: true
    )

    let session = WCSession.default

    override init() {
        super.init()
        session.delegate = self
        session.activate()
    }

    func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) { }

    func session(_ session: WCSession, didReceiveApplicationContext applicationContext: [String : Any]) {
        let next = (applicationContext["nextAppointment"] as? TimeInterval).map(Date.init(timeIntervalSince1970:))
        let decoded = WristBrief(
            displayName: applicationContext["displayName"] as? String ?? "",
            statusText: applicationContext["statusText"] as? String ?? "Unavailable",
            nextAppointment: next,
            alertCount: applicationContext["alertCount"] as? Int ?? 0,
            updatedAt: Date(timeIntervalSince1970: applicationContext["updatedAt"] as? TimeInterval ?? 0),
            isSensitiveHidden: applicationContext["isSensitiveHidden"] as? Bool ?? true
        )
        DispatchQueue.main.async { self.brief = decoded }
    }
}
