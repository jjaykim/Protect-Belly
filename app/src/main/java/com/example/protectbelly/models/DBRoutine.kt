package com.example.protectbelly.models

class DBRoutine {
    var routineName: String? = null;
    var numOfVariations = 0;
    var isActive = false;
    var workouts = ArrayList<DBWorkout>();

    constructor() {

    }

    constructor(routineName: String?, numOfVariations: Int, workouts: ArrayList<DBWorkout>, isActive:Boolean) {
        this.routineName = routineName
        this.numOfVariations = numOfVariations
        this.workouts = workouts
        this.isActive = isActive
    }

    override fun toString(): String {
        return "Routine(routineName=$routineName, numOfVariations=$numOfVariations, workouts=$workouts, isActive=$isActive)"
    }
}