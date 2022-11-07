package com.example.protectbelly.models

class WeightExercise: WorkoutExercise {
    var sets = 0;
    var reps = 0;
    var weight = 0;
    var incrementWeight = 0;
    var incrementFrequency = 0;

    constructor(
        sets: Int,
        reps: Int,
        weight: Int,
        incrementWeight: Int,
        incrementFrequency: Int
    ) : super() {
        this.sets = sets
        this.reps = reps
        this.weight = weight
        this.incrementWeight = incrementWeight
        this.incrementFrequency = incrementFrequency
    }

    constructor(
        workoutExerciseId: Int,
        name: String?,
        sets: Int,
        reps: Int,
        weight: Int,
        incrementWeight: Int,
        incrementFrequency: Int
    ) : super(workoutExerciseId, name) {
        this.sets = sets
        this.reps = reps
        this.weight = weight
        this.incrementWeight = incrementWeight
        this.incrementFrequency = incrementFrequency
    }
}