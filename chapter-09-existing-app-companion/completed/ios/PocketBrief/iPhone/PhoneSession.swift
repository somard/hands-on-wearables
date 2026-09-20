import Foundation
import WatchConnectivity

final class PhoneSession: NSObject, WCSessionDelegate {
    let session = WCSession.default

    override init() {
        super.init()
        session.delegate = self
        session.activate()
    }

    func publish(_ brief: WristBrief) throws {
        try session.updateApplicationContext([
            "displayName": brief.displayName,
            "statusText": brief.statusText,
            "nextAppointment": brief.nextAppointment?.timeIntervalSince1970 as Any,
            "alertCount": brief.alertCount,
            "updatedAt": brief.updatedAt.timeIntervalSince1970,
            "isSensitiveHidden": brief.isSensitiveHidden
        ])
    }

    func revoke() throws {
        try publish(WristBrief(
            displayName: "",
            statusText: "Sign in on iPhone",
            nextAppointment: nil,
            alertCount: 0,
            updatedAt: .now,
            isSensitiveHidden: true
        ))
    }

    func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) { }
    func sessionDidBecomeInactive(_ session: WCSession) { }
    func sessionDidDeactivate(_ session: WCSession) { session.activate() }
}
