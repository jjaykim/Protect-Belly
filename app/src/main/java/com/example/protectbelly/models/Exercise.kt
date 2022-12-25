package com.example.protectbelly.models

import com.example.protectbelly.models.enums.ExerciseType
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


    fun exerciseType(): ExerciseType {
        return if(category == 15){
            ExerciseType.CARDIO;
        } else if(equipment.contains(4) || equipment.contains(7) || equipment.contains(6) || equipment.contains(2) || equipment.isEmpty()) {
            ExerciseType.CALISTHENICS;
        } else {
            ExerciseType.WEIGHTLIFITING;
        }
    }

    override fun toString(): String {
        return "Exercise(exerciseName='$exerciseName', exerciseId=$exerciseId, category=$category, equipment=$equipment)"
    }
}