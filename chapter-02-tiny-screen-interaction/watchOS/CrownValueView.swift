import SwiftUI

// Book: Chapter 2, p.31
struct CrownValueView: View {
    @State private var crownValue = 0.0

    var body: some View {
        Text("\(Int(crownValue))")
            .focusable()
            .digitalCrownRotation(
                $crownValue,
                from: 0,
                through: 20,
                by: 1
            )
    }
}
