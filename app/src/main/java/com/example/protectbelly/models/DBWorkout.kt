package com.example.protectbelly.models

class DBWorkout {
    var workoutName: String? = null;
    var workoutExercises = ArrayList<DBWorkoutExercise>();

    constructor() {

    }

    constructor(workoutName: String?, workoutExercises: ArrayList<DBWorkoutExercise>) {
        this.workoutName = workoutName
        this.workoutExercises = workoutExercises
    }

    override fun toString(): String {
        return "DBWorkout(workoutName=$workoutName, workoutExercises=$workoutExercises)"
    }
}