import Foundation

// Book: Chapter 9, p.97.
struct WristBrief: Codable, Sendable {
    let displayName: String
    let statusText: String
    let nextAppointment: Date?
    let alertCount: Int
    let updatedAt: Date
    let isSensitiveHidden: Bool
}
