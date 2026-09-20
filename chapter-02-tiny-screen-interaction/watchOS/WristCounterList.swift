import SwiftUI

// Complete view around the book's p.29 List snippet.
struct WristCounterList: View {
    @State private var count = 0
    private var history: [String] { (1...max(count, 1)).map { "Count reached \($0)" } }

    var body: some View {
        List {
            Section("Count: \(count)") {
                Button("+1") { count += 1 }
                Button("Reset") { count = 0 }
            }
            Section("History") {
                ForEach(history, id: \.self) { entry in
                    Text(entry)
                }
            }
        }
    }
}
