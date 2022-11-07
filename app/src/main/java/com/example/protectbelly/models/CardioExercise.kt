package com.example.protectbelly.models

class CardioExercise: WorkoutExercise {
    var timeGoal = 0;
    var distanceGoal = 0;
    var heartRateGoal = 0;

    constructor(timeGoal: Int, distanceGoal: Int, heartRateGoal: Int) : super() {
        this.timeGoal = timeGoal
        this.distanceGoal = distanceGoal
        this.heartRateGoal = heartRateGoal
    }

    constructor(
        workoutExerciseId: Int,
        name: String?,
        timeGoal: Int,
        distanceGoal: Int,
        heartRateGoal: Int
    ) : super(workoutExerciseId, name) {
        this.timeGoal = timeGoal
        this.distanceGoal = distanceGoal
        this.heartRateGoal = heartRateGoal
    }
}