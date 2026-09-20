# Book-to-repository snippet map

This file is a mechanical audit aid for the companion code. The manuscript remains authoritative.

| Chapter | Manuscript pages | Repository files |
|---|---:|---|
| 1 Hello, Wrist | 20-21 | `chapter-01-hello-wrist/wearOS/HelloWrist.kt`, `watchOS/ContentView.swift` |
| 2 Tiny-Screen Interaction | 26-31 | `WristCounter.kt`, `WristCounterList.kt`, `RotaryValue.kt`, and watchOS counterparts |
| 3 Time, Haptics & Notifications | 36-41 | `wearOS/FocusTimer.kt`, `watchOS/FocusTimer.swift` |
| 4 Connected Data | 46-51 | `WeatherModels.kt`, `WeatherApi.kt`, `WeatherRepository.kt`, `WeatherGlance.swift` |
| 5 Phone <-> Watch | 56-61 | `CompanionSyncRepository.kt`, `WatchSession.swift`, `iPhoneSessionDelegate.swift` |
| 6 Actions & Handoff | 66-69 | manifest snippet, `Handoff.kt`, `Handoff.swift`, iPhone continuation example |
| 7 Beyond the App Screen | 76-81 | `NextEventTileService.kt`, `NextEventDataSource.kt`, `NextEvent.swift`, `NextEventWidget.swift` |
| 8 Sensors & Health | 87-91 | `WorkoutController.kt`, `WorkoutModel.swift` |
| 9 Existing-App Companion | 96-103 | starter PocketBrief apps + completed `WristBrief` companion implementations |

## Alignment rule

The repository keeps the manuscript's model names, key API calls, path names, state shapes, and UI hierarchy. Extra code exists only to provide surrounding imports, helper types, minimal state, and counterpart plumbing.

For example, Chapter 4 deliberately uses the manuscript's `@Serializable WeatherNow` + Retrofit `@GET("wear/now")` implementation rather than substituting a different HTTP stack. Chapter 9 deliberately uses the manuscript's `WristBrief` model rather than a second repository-only snapshot model.
