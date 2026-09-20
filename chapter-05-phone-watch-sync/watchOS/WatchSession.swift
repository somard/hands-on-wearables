import Foundation
import WatchConnectivity

// Book: Chapter 5, pp.57, 59, 61.
final class WatchSession: NSObject, ObservableObject, WCSessionDelegate {
    @Published var todayStatus = "Unknown"
    let session = WCSession.default

    override init() {
        super.init()
        session.delegate = self
        session.activate()
    }

    func session(
        _ session: WCSession,
        activationDidCompleteWith state: WCSessionActivationState,
        error: Error?
    ) { }

    func publishLatest(status: String) throws {
        try session.updateApplicationContext([
            "value": status,
            "updatedAt": Date().timeIntervalSince1970
        ])
    }

    func requestRefresh() {
        guard session.isReachable else { return }
        session.sendMessage(["command": "refresh"],
            replyHandler: { reply in
                if let value = reply["value"] as? String {
                    DispatchQueue.main.async { self.todayStatus = value }
                }
            }) { error in
                // show graceful fallback
                _ = error
            }
    }

    func session(_ session: WCSession, didReceiveApplicationContext applicationContext: [String : Any]) {
        guard let value = applicationContext["value"] as? String else { return }
        DispatchQueue.main.async { self.todayStatus = value }
    }
}
