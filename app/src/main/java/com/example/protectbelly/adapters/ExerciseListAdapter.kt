package com.example.protectbelly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.protectbelly.databinding.ExerciseListAdapterBinding
import com.example.protectbelly.models.CalisthenicExercise
import com.example.protectbelly.models.CardioExercise
import com.example.protectbelly.models.WeightExercise
import com.example.protectbelly.models.WorkoutExercise

class ExerciseListAdapter : RecyclerView.Adapter<ExerciseListAdapter.Companion.ItemViewHolder> {

    private val context: Context;
    var workoutExerciseList: ArrayList<WorkoutExercise>;

    constructor(context: Context, workoutExerciseList: ArrayList<WorkoutExercise>) {
        this.context = context
        this.workoutExerciseList = workoutExerciseList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ExerciseListAdapterBinding.inflate(
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
        class ItemViewHolder(binding: ExerciseListAdapterBinding) : RecyclerView.ViewHolder(binding.root) {
            var itemBinding: ExerciseListAdapterBinding = binding

            fun bind(context: Context, currItem: WorkoutExercise) {
                itemBinding.tvExerciseName.text = currItem.name;
                if(currItem is CardioExercise) {
                    itemBinding.tvSetsTime.text = currItem.timeGoal.toString() + " Minutes";
                    itemBinding.tvReps.visibility = View.GONE;
                    itemBinding.tvWeight.visibility = View.GONE;
                } else if(currItem is WeightExercise) {
                    itemBinding.tvSetsTime.text = currItem.sets.toString() + " Sets";
                    itemBinding.tvReps.text = currItem.reps.toString() + " Reps";
                    itemBinding.tvWeight.text = currItem.weight.toString() + "lbs";
                } else if(currItem is CalisthenicExercise) {
                    itemBinding.tvSetsTime.text = currItem.sets.toString() + " Sets";
                    itemBinding.tvReps.text = currItem.reps.toString() + " Reps";
                    itemBinding.tvWeight.visibility = View.GONE;
                }
            }
        }


    }


}