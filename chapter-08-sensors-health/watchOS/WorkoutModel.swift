import Foundation
import HealthKit
import Observation

// Book: Chapter 8, pp.87, 89, 91.
@Observable final class WorkoutModel {
    var bpm: Double?
    var elapsed: TimeInterval = 0
    var isPaused = false

    private let healthStore = HKHealthStore()
    private var session: HKWorkoutSession?
    private var builder: HKLiveWorkoutBuilder?

    func requestAuthorization() async throws {
        let heartRate = HKQuantityType(.heartRate)
        let workout = HKObjectType.workoutType()
        try await healthStore.requestAuthorization(
            toShare: [workout],
            read: [heartRate]
        )
    }

    func startWalk() async throws {
        let config = HKWorkoutConfiguration()
        config.activityType = .walking
        config.locationType = .outdoor
        let session = try HKWorkoutSession(
            healthStore: healthStore, configuration: config)
        let builder = session.associatedWorkoutBuilder()

        self.session = session
        self.builder = builder

        session.startActivity(with: Date())
        try await builder.beginCollection(at: Date())
    }

    func endWalk() async throws {
        session?.end()
        try await builder?.endCollection(at: Date())
        _ = try await builder?.finishWorkout()
        session = nil
        builder = nil
    }
}
