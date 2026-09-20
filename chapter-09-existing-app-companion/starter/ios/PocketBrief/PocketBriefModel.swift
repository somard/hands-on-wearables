import Foundation

struct PocketBriefModel {
    var displayName = "Taylor"
    var statusText = "Available"
    var nextAppointment: Date? = Date().addingTimeInterval(3600)
    var alertCount = 1
    var signedIn = true
}
