import WatchConnectivity

// iPhone-side lifecycle callbacks called out on p.57.
final class iPhoneSessionDelegate: NSObject, WCSessionDelegate {
    let session = WCSession.default

    override init() {
        super.init()
        session.delegate = self
        session.activate()
    }

    func session(_ session: WCSession, activationDidCompleteWith activationState: WCSessionActivationState, error: Error?) { }
    func sessionDidBecomeInactive(_ session: WCSession) { }
    func sessionDidDeactivate(_ session: WCSession) { session.activate() }
}
