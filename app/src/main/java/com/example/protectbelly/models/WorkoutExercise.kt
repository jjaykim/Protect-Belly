package com.example.protectbelly.models

import java.io.Serializable

open class WorkoutExercise:Serializable {
    var name:String? = null;

    constructor() {

    }

    constructor(name: String?) {
        this.name = name
    }
}