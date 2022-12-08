package com.example.protectbelly.models

class CardioExercise: WorkoutExercise {
    var timeGoal = 0;
    var distanceGoal = 0;
    var heartRateGoal = 0;

    constructor():super(){

    }

    constructor(timeGoal: Int, distanceGoal: Int, heartRateGoal: Int) : super() {
        this.timeGoal = timeGoal
        this.distanceGoal = distanceGoal
        this.heartRateGoal = heartRateGoal
    }

    constructor(
        name: String?,
        timeGoal: Int,
        distanceGoal: Int,
        heartRateGoal: Int
    ) : super(name) {
        this.timeGoal = timeGoal
        this.distanceGoal = distanceGoal
        this.heartRateGoal = heartRateGoal
    }

    override fun toString(): String {
        return "CardioExercise(timeGoal=$timeGoal, distanceGoal=$distanceGoal, heartRateGoal=$heartRateGoal)"
    }
}