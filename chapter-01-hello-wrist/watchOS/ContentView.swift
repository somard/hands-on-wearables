import SwiftUI

// Book: Chapter 1, p.21
struct ContentView: View {
    @State private var taps = 0

    var body: some View {
        VStack {
            Text("Hello, Wrist!")
            Text("Taps: \(taps)")
            Button("Tap") {
                taps += 1
            }
        }
    }
}
