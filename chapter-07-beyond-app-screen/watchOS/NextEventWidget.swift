import SwiftUI
import WidgetKit

struct NextEventEntry: TimelineEntry {
    let date: Date
    let event: NextEvent
}

struct Provider: TimelineProvider {
    func placeholder(in context: Context) -> NextEventEntry {
        NextEventEntry(date: .now, event: NextEvent(title: "Design review", start: .now))
    }

    func getSnapshot(in context: Context, completion: @escaping (NextEventEntry) -> Void) {
        completion(placeholder(in: context))
    }

    func getTimeline(in context: Context, completion: @escaping (Timeline<NextEventEntry>) -> Void) {
        let entry = NextEventEntry(date: .now, event: NextEvent(title: "Design review", start: .now))
        completion(Timeline(entries: [entry], policy: .after(.now.addingTimeInterval(900))))
    }
}

struct NextEventView: View {
    let entry: NextEventEntry

    var body: some View {
        Text(entry.event.title)
    }
}

// Book: Chapter 7, pp.79 and 81. p.79 introduces accessoryRectangular;
// p.81 expands the same widget to complication-capable accessory families.
struct NextEventWidget: Widget {
    var body: some WidgetConfiguration {
        StaticConfiguration(
            kind: "NextEvent",
            provider: Provider()) { entry in
                NextEventView(entry: entry)
            }
            .supportedFamilies([
                .accessoryCircular,
                .accessoryRectangular,
                .accessoryInline
            ])
    }
}
