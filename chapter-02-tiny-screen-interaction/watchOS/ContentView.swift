import SwiftUI

// Book: Chapter 2, p.27
struct ContentView: View {
    @State private var count = 0

    var body: some View {
        VStack {
            Text("Count")
            Text("\(count)")
                .font(.title)
            Button("+1") { count += 1 }
            Button("Reset") { count = 0 }
        }
    }
}
