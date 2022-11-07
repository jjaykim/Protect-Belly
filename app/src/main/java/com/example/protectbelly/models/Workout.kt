package com.example.protectbelly.models

class Workout {
    var workoutId = 0;
    var workoutName: String? = null;
    var workoutExercises = ArrayList<WorkoutExercise>();

    constructor() {

    }

    constructor(
        workoutId: Int,
        workoutName: String?,
        workoutExercises: ArrayList<WorkoutExercise>
    ) {
        this.workoutId = workoutId
        this.workoutName = workoutName
        this.workoutExercises = workoutExercises
    }
}