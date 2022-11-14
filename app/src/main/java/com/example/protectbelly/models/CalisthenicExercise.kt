package com.example.protectbelly.models

class CalisthenicExercise:WorkoutExercise {
    var sets = 0;
    var reps = 0;

    constructor():super() {

    }
    constructor(sets: Int, reps: Int) : super() {
        this.sets = sets
        this.reps = reps
    }

    constructor(name: String?, sets: Int, reps: Int) : super(
        name
    ) {
        this.sets = sets
        this.reps = reps
    }

    override fun toString(): String {
        return "CalisthenicExercise(sets=$sets, reps=$reps)"
    }
}