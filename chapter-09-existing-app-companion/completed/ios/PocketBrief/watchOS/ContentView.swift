import SwiftUI

// Book: Chapter 9, p.99.
struct ContentView: View {
    @StateObject private var session = WatchSession()

    var body: some View {
        let brief = session.brief
        List {
            Text("Good morning, \(brief.displayName)")
            StatusRow(brief: brief)
            if let appointment = brief.nextAppointment {
                AppointmentRow(date: appointment)
            }
            Button("Continue on iPhone") {
                handoff(brief)
            }
        }
    }
}

private struct StatusRow: View {
    let brief: WristBrief
    var body: some View {
        Text(brief.isSensitiveHidden ? "Open PocketBrief" : brief.statusText)
    }
}

private struct AppointmentRow: View {
    let date: Date
    var body: some View { Text(date, style: .time) }
}

private func handoff(_ brief: WristBrief) {
    let activity = NSUserActivity(activityType: "com.example.pocketbrief.details")
    activity.title = "PocketBrief details"
    activity.userInfo = ["updatedAt": brief.updatedAt.timeIntervalSince1970]
    activity.becomeCurrent()
}
