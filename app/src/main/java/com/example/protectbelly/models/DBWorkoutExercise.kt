package com.example.protectbelly.models

class DBWorkoutExercise {
    var name:String? = null;
    var timeGoal = 0;
    var distanceGoal = 0;
    var heartRateGoal = 0;
    var sets = 0;
    var reps = 0;
    var weight = 0;
    var incrementWeight = 0;
    var incrementFrequency = 0;


    override fun toString(): String {
        return "DBWorkoutExercise(name=$name, timeGoal=$timeGoal, distanceGoal=$distanceGoal, heartRateGoal=$heartRateGoal, sets=$sets, reps=$reps, weight=$weight, incrementWeight=$incrementWeight, incrementFrequency=$incrementFrequency)"
    }


}