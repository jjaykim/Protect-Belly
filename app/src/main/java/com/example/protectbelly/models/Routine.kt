package com.example.protectbelly.models

import java.io.Serializable

class Routine: Serializable {
    var routineName: String? = null;
    var numOfVariations = 0;
    var isActive = false;
    var workouts = ArrayList<Workout>();

    constructor() {

    }

    constructor(routineName: String?, numOfVariations: Int, workouts: ArrayList<Workout>, isActive:Boolean) {
        this.routineName = routineName
        this.numOfVariations = numOfVariations
        this.workouts = workouts
        this.isActive = isActive
    }

    fun convertDBObject(dbRoutine: DBRoutine) {
        routineName = dbRoutine.routineName;
        numOfVariations = dbRoutine.numOfVariations;
        isActive = dbRoutine.isActive;
        dbRoutine.workouts.forEach { dbWorkout ->

            var workout = Workout();
            workout.workoutName = dbWorkout.workoutName;

            dbWorkout.workoutExercises.forEach { dbWorkoutExercise ->
                if(dbWorkoutExercise.distanceGoal != 0) {
                    var cardioExercise = CardioExercise(dbWorkoutExercise.name, dbWorkoutExercise.timeGoal, dbWorkoutExercise.distanceGoal, dbWorkoutExercise.heartRateGoal);
                    workout.workoutExercises.add(cardioExercise);
                } else if(dbWorkoutExercise.incrementFrequency != 0) {
                    var weightExercise = WeightExercise(dbWorkoutExercise.name, dbWorkoutExercise.sets, dbWorkoutExercise.reps, dbWorkoutExercise.weight, dbWorkoutExercise.incrementWeight, dbWorkoutExercise.incrementFrequency);
                    workout.workoutExercises.add(weightExercise);
                } else if(dbWorkoutExercise.reps != 0) {
                    var calisthenicExercise = CalisthenicExercise(dbWorkoutExercise.name, dbWorkoutExercise.sets,dbWorkoutExercise.reps);
                    workout.workoutExercises.add(calisthenicExercise);
                }
            }
            workouts.add(workout);
        }
    }

    override fun toString(): String {
        return "Routine(routineName=$routineName, numOfVariations=$numOfVariations, workouts=$workouts, isActive=$isActive)"
    }
}