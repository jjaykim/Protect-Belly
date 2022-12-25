package com.example.protectbelly.models

import java.io.Serializable

class Workout:Serializable {
    var workoutName: String? = null;
    var workoutExercises = ArrayList<WorkoutExercise>();

    constructor() {

    }

    constructor(
        workoutName: String?,
        workoutExercises: ArrayList<WorkoutExercise>
    ) {
        this.workoutName = workoutName
        this.workoutExercises = workoutExercises
    }

    override fun toString(): String {
        return "Workout(workoutName=$workoutName, workoutExercises=$workoutExercises)"
    }
}