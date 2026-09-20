import SwiftUI

struct ContentView: View {
    @State private var model = PocketBriefModel()

    var body: some View {
        NavigationStack {
            List {
                if model.signedIn {
                    Text("Good morning, \(model.displayName)")
                    Text(model.statusText)
                    Text("\(model.alertCount) alert(s)")
                    Button("Sign out") { model.signedIn = false }
                } else {
                    Button("Sign in") { model = PocketBriefModel() }
                }
            }
            .navigationTitle("PocketBrief")
        }
    }
}
