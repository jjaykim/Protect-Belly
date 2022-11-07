package com.example.protectbelly.models

import com.google.gson.annotations.SerializedName

class Exercise {
    @SerializedName("name")
    var exerciseName:String? = null;
    @SerializedName("id")
    var exerciseId = 0
    @SerializedName("category")
    var category = 0
    @SerializedName("equipment")
    lateinit var equipment: ArrayList<Int>;


    override fun toString(): String {
        return "Exercise(exerciseName='$exerciseName', exerciseId=$exerciseId, category=$category, equipment=$equipment)"
    }
}