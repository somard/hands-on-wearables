import SwiftUI

// Minimal iPhone receiving example for the activity created by the watch.
struct PhoneRootView: View {
    @State private var selectedItemID: String?

    var body: some View {
        NavigationStack {
            Text(selectedItemID.map { "Item \($0)" } ?? "Home")
        }
        .onContinueUserActivity("com.example.view-item") { activity in
            selectedItemID = activity.userInfo?["itemID"] as? String
        }
    }
}
