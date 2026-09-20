import SwiftUI
import WidgetKit

struct BriefEntry: TimelineEntry {
    let date: Date
    let brief: WristBrief
}

struct BriefProvider: TimelineProvider {
    func placeholder(in context: Context) -> BriefEntry {
        BriefEntry(date: .now, brief: WristBrief(displayName: "Taylor", statusText: "Available", nextAppointment: .now, alertCount: 1, updatedAt: .now, isSensitiveHidden: false))
    }
    func getSnapshot(in context: Context, completion: @escaping (BriefEntry) -> Void) { completion(placeholder(in: context)) }
    func getTimeline(in context: Context, completion: @escaping (Timeline<BriefEntry>) -> Void) {
        completion(Timeline(entries: [placeholder(in: context)], policy: .after(.now.addingTimeInterval(900))))
    }
}

struct PocketBriefWidget: Widget {
    var body: some WidgetConfiguration {
        StaticConfiguration(kind: "PocketBrief", provider: BriefProvider()) { entry in
            if entry.brief.isSensitiveHidden {
                Text("PocketBrief")
            } else {
                VStack {
                    Text(entry.brief.statusText)
                    Text("\(entry.brief.alertCount) alert(s)")
                }
            }
        }
        .supportedFamilies([.accessoryCircular, .accessoryRectangular, .accessoryInline])
    }
}
