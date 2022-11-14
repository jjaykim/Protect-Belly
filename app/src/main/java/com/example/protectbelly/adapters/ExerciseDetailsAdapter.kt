package com.example.protectbelly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.protectbelly.databinding.ExerciseDetailsAdapterBinding
import com.example.protectbelly.models.CalisthenicExercise
import com.example.protectbelly.models.CardioExercise
import com.example.protectbelly.models.WeightExercise
import com.example.protectbelly.models.WorkoutExercise

class ExerciseDetailsAdapter: RecyclerView.Adapter<ExerciseDetailsAdapter.Companion.ItemViewHolder> {

    private val context: Context;
    var workoutExerciseList: ArrayList<WorkoutExercise>;

    constructor(context: Context, workoutExerciseList: ArrayList<WorkoutExercise>) {
        this.context = context
        this.workoutExerciseList = workoutExerciseList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ExerciseDetailsAdapterBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val currItem: WorkoutExercise = workoutExerciseList[position];
        holder.bind(context, currItem)
    }

    override fun getItemCount(): Int {
        return workoutExerciseList.size;
    }

    companion object{
        class ItemViewHolder(binding: ExerciseDetailsAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
            var itemBinding: ExerciseDetailsAdapterBinding = binding

            fun bind(context: Context, currItem: WorkoutExercise) {
                itemBinding.tvExerciseName.text = currItem.name;
                if(currItem is CalisthenicExercise) {
                    itemBinding.tvTopLeft.text = "Reps: ${currItem.reps}"
                    itemBinding.tvTopRight.text = "Sets: ${currItem.sets}"
                    itemBinding.tvBottomLeft.visibility = View.GONE
                    itemBinding.tvBottomRight.visibility = View.GONE
                    itemBinding.tvBottom.visibility = View.GONE
                } else if (currItem is WeightExercise) {
                    itemBinding.tvTopLeft.text = "Reps: ${currItem.reps}"
                    itemBinding.tvTopRight.text = "Sets: ${currItem.sets}"
                    itemBinding.tvBottomLeft.text = "Weight: ${currItem.weight} lbs"
                    itemBinding.tvBottomRight.text = "Weight Increment: ${currItem.incrementWeight}"
                    itemBinding.tvBottom.text = "Increment Frequency: ${currItem.incrementFrequency}"
                } else if (currItem is CardioExercise) {
                    itemBinding.tvTopLeft.text = "Time Goal: ${currItem.timeGoal} minutes"
                    itemBinding.tvTopRight.text = "Distance Goal: ${currItem.distanceGoal} km"
                    if(currItem.heartRateGoal != 0) {
                        itemBinding.tvBottomLeft.text = "Heart-rate Goal: ${currItem.heartRateGoal} bpm"
                    }
                }
            }
        }


    }
}