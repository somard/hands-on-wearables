import SwiftUI
import UserNotifications
import WatchKit

// Book: Chapter 3, pp.37, 39, 41.
struct FocusTimer: View {
    @State private var endAt: Date?

    var secondsLeft: Int {
        guard let endAt else { return 0 }
        return max(0, Int(endAt.timeIntervalSinceNow))
    }

    var body: some View {
        VStack {
            TimelineView(.periodic(from: .now, by: 1)) { _ in
                Text(endAt == nil ? "Ready" : "\(secondsLeft) s")
            }

            Button("Start") {
                let seconds: TimeInterval = 60
                endAt = Date().addingTimeInterval(seconds)
                WKInterfaceDevice.current().play(.success)
                Task { try? await scheduleCompletion(in: seconds) }
            }
        }
    }
}

private func scheduleCompletion(in seconds: TimeInterval) async throws {
    let center = UNUserNotificationCenter.current()
    let content = UNMutableNotificationContent()
    content.title = "Focus complete"
    content.body = "Your wrist timer finished."
    content.sound = .default
    let trigger = UNTimeIntervalNotificationTrigger(
        timeInterval: seconds, repeats: false)
    let request = UNNotificationRequest(
        identifier: "focus-finished", content: content, trigger: trigger)
    try await center.add(request)
}
