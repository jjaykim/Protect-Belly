package com.example.protectbelly.models

import java.io.Serializable

class Routine: Serializable {
    var routineName: String? = null;
    var numOfVariations = 0;
    var workouts = ArrayList<Workout>();

    constructor() {

    }

    constructor(routineName: String?, numOfVariations: Int, workouts: ArrayList<Workout>) {
        this.routineName = routineName
        this.numOfVariations = numOfVariations
        this.workouts = workouts
    }

    override fun toString(): String {
        return "Routine(routineName=$routineName, numOfVariations=$numOfVariations, workouts=$workouts)"
    }
}