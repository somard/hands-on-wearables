import Foundation

// Book: Chapter 6, p.67.
func handoffItem42() {
    let activity = NSUserActivity(
        activityType: "com.example.view-item")
    activity.title = "View item"
    activity.userInfo = ["itemID": "42"]
    activity.becomeCurrent()
}
