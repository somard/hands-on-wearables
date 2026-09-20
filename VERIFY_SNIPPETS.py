from pathlib import Path
import sys

ROOT = Path(__file__).resolve().parent

checks = {
    "chapter-01-hello-wrist/wearOS/HelloWrist.kt": [
        'var taps by remember { mutableIntStateOf(0) }',
        'Text("Hello, Wrist!")',
        'Button(onClick = { taps++ })',
    ],
    "chapter-01-hello-wrist/watchOS/ContentView.swift": [
        '@State private var taps = 0',
        'Text("Taps: \\(taps)")',
        'Button("Tap")',
    ],
    "chapter-02-tiny-screen-interaction/wearOS/WristCounter.kt": [
        'var count by remember { mutableIntStateOf(0) }',
        'Button(onClick = { count = 0 })',
    ],
    "chapter-02-tiny-screen-interaction/wearOS/RotaryValue.kt": [
        '.onRotaryScrollEvent { event ->',
        'count = count.coerceIn(0, 20)',
        'focusRequester.requestFocus()',
    ],
    "chapter-02-tiny-screen-interaction/watchOS/CrownValueView.swift": [
        '.digitalCrownRotation(',
        '$crownValue,',
        'through: 20,',
    ],
    "chapter-03-time-haptics-notifications/wearOS/FocusTimer.kt": [
        'val endAt = remember { mutableStateOf<Instant?>(null) }',
        'val now = remember { mutableStateOf(Clock.System.now()) }',
        'HapticFeedbackType.Confirm',
        '.setContentTitle("Focus complete")',
    ],
    "chapter-03-time-haptics-notifications/watchOS/FocusTimer.swift": [
        '@State private var endAt: Date?',
        'WKInterfaceDevice.current().play(.success)',
        'identifier: "focus-finished"',
    ],
    "chapter-04-connected-data/wearOS/WeatherModels.kt": [
        '@Serializable',
        'data class WeatherNow(',
        'sealed interface WeatherUiState',
        'data class CachedWeather(',
    ],
    "chapter-04-connected-data/wearOS/WeatherApi.kt": [
        '@GET("wear/now")',
        'suspend fun now(): WeatherNow',
    ],
    "chapter-04-connected-data/watchOS/WeatherGlance.swift": [
        'struct WeatherNow: Codable',
        'let (data, response) = try await URLSession.shared.data(from: url)',
        'enum WeatherState',
        'struct CachedWeather: Codable',
    ],
    "chapter-05-phone-watch-sync/wearOS/CompanionSyncRepository.kt": [
        'val dataClient = Wearable.getDataClient(context)',
        'PutDataMapRequest.create("/today/status")',
        '"/today/refresh"',
    ],
    "chapter-05-phone-watch-sync/watchOS/WatchSession.swift": [
        'let session = WCSession.default',
        'try session.updateApplicationContext([',
        'guard session.isReachable else { return }',
        'session.sendMessage(["command": "refresh"]',
    ],
    "chapter-06-actions-handoff/wearOS/Handoff.kt": [
        'val helper = RemoteActivityHelper(context, executor)',
        'Uri.parse("https://example.com/items/42")',
        'helper.startRemoteActivity(intent, nodeId)',
    ],
    "chapter-06-actions-handoff/watchOS/Handoff.swift": [
        'activityType: "com.example.view-item"',
        'activity.userInfo = ["itemID": "42"]',
        'activity.becomeCurrent()',
    ],
    "chapter-07-beyond-app-screen/wearOS/NextEventTileService.kt": [
        'private const val RESOURCES_VERSION = "1"',
        'text("10:30 Design review".layoutString)',
        'Resources.Builder().setVersion(RESOURCES_VERSION).build()',
    ],
    "chapter-07-beyond-app-screen/wearOS/NextEventDataSource.kt": [
        'SuspendingComplicationDataSourceService()',
        'PlainComplicationText.Builder("10:30").build()',
    ],
    "chapter-07-beyond-app-screen/watchOS/NextEventWidget.swift": [
        'struct NextEventWidget: Widget',
        '.accessoryCircular,',
        '.accessoryRectangular,',
        '.accessoryInline',
    ],
    "chapter-08-sensors-health/wearOS/WorkoutController.kt": [
        'val health = HealthServices.getClient(context)',
        'ExerciseConfig.Builder(ExerciseType.WALKING)',
        'setOf(DataType.HEART_RATE_BPM)',
        'data class WorkoutUiState(',
    ],
    "chapter-08-sensors-health/watchOS/WorkoutModel.swift": [
        'let heartRate = HKQuantityType(.heartRate)',
        'let session = try HKWorkoutSession(',
        '@Observable final class WorkoutModel',
    ],
    "chapter-09-existing-app-companion/completed/android/PocketBrief/shared/WristBrief.kt": [
        '@Serializable',
        'data class WristBrief(',
        'val isSensitiveHidden: Boolean',
    ],
    "chapter-09-existing-app-companion/completed/android/PocketBrief/wearOS/PocketBriefScreen.kt": [
        'Text("Good morning, ${brief.displayName}")',
        'brief.nextAppointmentEpochMs?.let { time ->',
        'Text("Open details on phone")',
    ],
    "chapter-09-existing-app-companion/completed/ios/PocketBrief/shared/WristBrief.swift": [
        'struct WristBrief: Codable, Sendable',
        'let isSensitiveHidden: Bool',
    ],
    "chapter-09-existing-app-companion/completed/ios/PocketBrief/watchOS/ContentView.swift": [
        'Text("Good morning, \\(brief.displayName)")',
        'if let appointment = brief.nextAppointment',
        'Button("Continue on iPhone")',
    ],
}

failed = []
for rel, needles in checks.items():
    path = ROOT / rel
    if not path.exists():
        failed.append(f"MISSING FILE: {rel}")
        continue
    text = path.read_text(encoding="utf-8")
    for needle in needles:
        # verification strings use doubled backslashes only so Python literals survive cleanly
        normalized = needle.replace('\\\\', '\\')
        if normalized not in text:
            failed.append(f"MISSING SNIPPET: {rel}: {normalized}")

if failed:
    print("BOOK/REPO ALIGNMENT CHECK FAILED")
    for item in failed:
        print(" -", item)
    sys.exit(1)

print(f"BOOK/REPO ALIGNMENT CHECK PASSED ({len(checks)} files checked)")
