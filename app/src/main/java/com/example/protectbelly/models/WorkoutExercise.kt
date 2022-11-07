package com.example.protectbelly.models

open class WorkoutExercise {
    var workoutExerciseId = 0;
    var name:String? = null;

    constructor() {

    }

    constructor(workoutExerciseId: Int, name: String?) {
        this.workoutExerciseId = workoutExerciseId
        this.name = name
    }
}