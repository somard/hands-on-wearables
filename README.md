# Hands-On Wearables

**Wear OS ↔ watchOS**  
**9 practical projects · 2 native platforms · 1 parallel tutorial**

Companion source code for *Hands-On Wearables* by Somard Kruayatidee.

This repository mirrors the book’s central idea:

- **Wear OS** examples use Kotlin and Jetpack Compose.
- **watchOS** examples use Swift and SwiftUI.
- Each chapter explores the **same engineering goal on both platforms**.
- The implementations are intentionally native to each ecosystem rather than forced into line-for-line equivalence.

> **Compare intent, not punctuation.**

---

## What This Repository Is For

Modern coding assistants can generate a surprising amount of implementation code quickly.

That makes syntax easier to obtain. It does **not** remove the need for engineering judgment.

A wearable engineer still has to decide:

- Is this the right API?
- Who owns the state?
- What is the source of truth?
- What happens when the watch is suspended?
- What happens when the phone is unreachable?
- What happens when data is stale?
- What belongs on the wrist?
- What should remain on the phone?
- Does the implementation behave like a native wearable experience?
- Is the solution appropriate for battery, privacy, permissions, and lifecycle constraints?

The examples in this repository are intentionally small enough to study, run, modify, break, debug, and rebuild.

Use them as **reference implementations for the engineering ideas in the book**, not as code to memorize.

---

## Repository Structure

Chapters 1 through 8 use parallel platform folders.

```text
hands-on-wearables/
│
├── chapter-01-hello-wrist/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-02-tiny-screen-interaction/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-03-time-haptics-notifications/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-04-connected-data/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-05-phone-watch-sync/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-06-actions-handoff/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-07-beyond-app-screen/
│   ├── wearOS/
│   └── watchOS/
│
├── chapter-08-sensors-health/
│   ├── wearOS/
│   └── watchOS/
│
└── chapter-09-existing-app-companion/
    ├── starter/
    │   ├── android/
    │   │   └── PocketBrief/
    │   └── ios/
    │       └── PocketBrief/
    │
    └── completed/
        ├── android/
        │   └── PocketBrief/
        │       ├── app/
        │       └── wearOS/
        │
        └── ios/
            └── PocketBrief/
                ├── iPhone app/
                └── watchOS target/
```

Chapter 9 is intentionally different.

Instead of starting from another empty wearable project, it begins with small pre-existing Android and iPhone applications and extends them with wearable companions. That better reflects how wearable work commonly enters an established mobile product.

---

## Chapter 1 — Hello, Wrist

Build and run the smallest native watch application on both platforms.

### Wear OS

- Android Studio
- Wear OS emulator
- Kotlin
- Jetpack Compose
- Android modules and run configurations

### watchOS

- Xcode
- Apple Watch Simulator
- Swift
- SwiftUI
- Xcode targets, schemes, and run destinations

The greeting itself is not the lesson.

The goal is to establish a clean, working toolchain before adding wearable-specific behavior and to understand what the project generators created.

---

## Chapter 2 — Tiny-Screen Interaction

Build the same small interaction while respecting each platform.

Topics include:

- local UI state
- watch-sized hierarchy
- touch targets
- vertical scrolling
- rotary input
- Digital Crown interaction

The two versions should solve the same user problem without being artificially identical.

> **Rule: equivalent task, native behavior.**

---

## Chapter 3 — Time, Haptics & Notifications

Build a small focus timer and explore what happens after the user stops looking at the screen.

Topics include:

- deadline-based time
- lifecycle-aware behavior
- haptic feedback
- local notifications
- suspension and background constraints
- battery-aware design

A timer that works only while the screen remains active is not yet a good wearable timer.

---

## Chapter 4 — Connected Data

Build a compact network-backed weather glance.

Topics include:

- HTTP networking
- asynchronous work
- loading, success, and failure state
- caching
- freshness
- intermittent connectivity
- wrist-sized payloads

The watch should request only what the wrist actually needs.

A stale-but-clearly-labeled answer can be more useful than an endless spinner.

---

## Chapter 5 — Phone ↔ Watch: Companion Sync

Connect an existing phone application to its wearable companion.

### Wear OS

- Data Layer
- `DataClient`
- `MessageClient`
- phone/watch state synchronization

### watchOS

- `WatchConnectivity`
- `WCSession`
- immediate and deferred delivery

The central question is not simply:

> How do I send data?

It is:

> What kind of information am I sending?

Each transfer should be understood as one of several communication shapes, such as:

- latest state
- an immediate command
- queued or deferred delivery

The transport API should follow the delivery semantics.

---

## Chapter 6 — Actions & Handoff

Start an interaction on the wrist and continue richer work on the phone.

Topics include:

- deep links
- remote activity
- Handoff
- context transfer
- graceful fallback
- phone/watch product boundaries

A mature wearable experience knows when to stop.

The watch contributes immediacy and context. The phone contributes screen space and richer interaction.

---

## Chapter 7 — Beyond the App Screen

Deliver value without requiring the user to open the main watch application.

### Wear OS

- Tiles
- complications

### watchOS

- Smart Stack widgets
- complications

One domain model can support several wearable surfaces, but that does not mean one UI should be copied everywhere.

Each surface has a different information budget.

The engineering question is:

> Which moment belongs in the app, a Tile or widget, a complication, or a notification?

---

## Chapter 8 — Sensors & Health

Build a small workout and live heart-rate experience using platform health frameworks.

Topics include:

- Health Services
- HealthKit
- permissions and capabilities
- workout lifecycle
- live metrics
- privacy
- battery use

Health and sensor code deserves careful review.

A screen can compile successfully while still making poor choices about permissions, session lifecycle, privacy, or measurement semantics.

---

## Chapter 9 — Extending an Existing Mobile App

The capstone project begins from an application that already exists.

The repository provides small starter applications for both Android and iPhone so the chapter is reproducible.

```text
chapter-09-existing-app-companion/
│
├── starter/
│   ├── android/PocketBrief/
│   └── ios/PocketBrief/
│
└── completed/
    ├── android/PocketBrief/
    └── ios/PocketBrief/
```

### The Starter App

`PocketBrief` is a deliberately small fictional mobile application.

It already contains enough ordinary phone-app behavior to make the extension realistic:

- a simple signed-in / signed-out state
- a home screen
- a “next appointment” or similar upcoming item
- a small Today/status model
- navigation
- a richer detail screen that belongs on the phone
- local or mocked data
- **no wearable support**

Treat it as an application your team has already been maintaining.

The task is not to rebuild it for a smaller display.

The task is to identify the few moments that become more useful when moved to the wrist.

### The Completed App

The completed version adds:

- a Wear OS companion to the Android application
- a watchOS companion to the iPhone application
- minimal synchronized wrist state
- phone-owned authentication
- stale-state handling
- sign-out/revocation behavior
- privacy considerations
- handoff back to the phone
- glanceable wearable surfaces

The capstone question is not:

> How much of the phone app can fit on the watch?

It is:

> Which moments genuinely become better on the wrist?

---

## How to Use the Code

There are several reasonable ways to work through the repository.

### Wear OS Path

Follow each chapter’s `wearOS/` directory.

This path is useful if you primarily develop for Android and want to learn the wearable extension of familiar Android concepts.

### watchOS Path

Follow each chapter’s `watchOS/` directory.

This path is useful if you primarily develop for Apple platforms or want to concentrate on SwiftUI and watchOS.

### Parallel Path

Open the Wear OS and watchOS implementations side by side.

Compare:

- the user goal
- state ownership
- lifecycle
- platform APIs
- connectivity assumptions
- hardware interaction
- native surface choices

Do not expect identical code.

The point of the parallel layout is to expose where the two platforms solve the same problem similarly and where their models differ.

---

## Using an AI Coding Assistant

You are welcome to use an LLM or coding assistant while working through these projects.

The most useful role for AI here is **not** to reproduce the completed reference project verbatim.

Instead, use it to help you:

- scaffold an unfamiliar API
- explain generated code
- diagnose build or runtime failures
- adapt an example to a new requirement
- propose an alternative implementation
- refactor a completed project
- add an edge case
- create a test
- explore a platform-specific alternative

For example, after completing a project, ask your coding assistant to change a requirement:

- make cached weather visibly stale after a chosen interval
- handle permission denial without breaking the health UI
- change a companion transfer from latest-state semantics to queued delivery
- carry an item identifier through handoff
- add a new wearable surface
- decide whether a new phone feature deserves any watch representation at all

Then review the result against the engineering model in the chapter.

Do not stop at:

> “It compiles.”

Ask instead:

- Why this API?
- Who owns this state?
- What happens when conditions change?
- Does this respect lifecycle constraints?
- Is the connectivity assumption valid?
- Is the permission justified?
- Is this actually a wearable interaction?
- Should this feature remain on the phone?

> **Let the machine generate syntax. You still own the architecture, constraints, and judgment.**

---

## About the Reference Implementations

The code in this repository is provided as a compact, runnable reference for the concepts described in the book.

For small platform examples, there may be only a few natural ways to express a solution. Code produced independently by developers or coding assistants may therefore resemble these examples closely.

That is not the comparison this repository is designed to make.

The important questions are whether an implementation makes sound decisions about:

- state
- lifecycle
- delivery semantics
- connectivity
- offline behavior
- battery
- privacy
- permissions
- phone/watch responsibility
- native platform behavior

The reference implementation is one implementation of those decisions, not the only acceptable syntax.

---

## AI-Assisted Development Note

Modern coding assistants were used as part of the development and review workflow for portions of the sample code and supporting material.

The examples are organized around the engineering concepts presented in the book and are intended to be reviewed, tested, adapted, and understood rather than accepted merely because they were generated or because they compile.

The value of the parallel tutorial is in understanding the two platform models, the architectural decisions behind them, and the boundaries between phone and watch.

---

## Requirements

### Wear OS

You will generally need:

- a current stable Android Studio installation
- Kotlin support
- Jetpack Compose / Compose for Wear OS
- Android SDK Platform-Tools
- a Wear OS emulator image and virtual watch, or a physical Wear OS device

### watchOS

You will generally need:

- a Mac
- a current stable Xcode installation
- Swift and SwiftUI
- watchOS platform support
- an Apple Watch Simulator runtime, or a physical Apple Watch through the normal Apple development workflow

IDE versions, SDK versions, menu names, and platform APIs evolve.

Use the current stable platform tooling unless a chapter explicitly states otherwise.

---

## Real Hardware

Simulators and emulators are excellent for development, but they cannot fully reproduce:

- wrist ergonomics
- haptic feel
- sensor behavior
- battery characteristics
- wrist-raise interaction
- physical crown or rotary behavior
- real phone/watch connectivity conditions

Before treating a wearable feature as production-ready, test it on physical hardware.

---

## Book

This repository accompanies:

# *Hands-On Wearables*

**Wear OS ↔ watchOS**  
**9 practical projects · 2 native platforms · 1 parallel tutorial**

Repository:

https://github.com/somard/hands-on-wearables

---

## Author

**Somard Kruayatidee**

Software engineer with experience across desktop systems, security software, mobile application development, and wearable computing.

The book approaches wearable development from a working engineer’s perspective: extend useful mobile moments to the wrist without simply shrinking a phone application onto a smaller screen.

---

## Repository Status

The repository is being populated alongside the final book release.

Examples are intended to remain:

- small
- runnable
- chapter-focused
- easy to inspect
- easy to modify

Where platform APIs or tooling evolve, the repository may be updated independently of the printed edition.

---

## Issues and Corrections

If you find:

- a build issue
- an API change
- a documentation mismatch
- an unclear explanation
- a reproducible platform difference

please open a GitHub issue with:

1. the chapter
2. the platform
3. the IDE/SDK version
4. the behavior you observed
5. the expected behavior
6. a minimal reproduction when possible

Corrections and platform updates are welcome.

---

## License

Source-code licensing information will be finalized before the public release.

Until a license file is added, do not assume that the absence of a license grants unrestricted reuse.

---

**Compare intent, not punctuation.**
