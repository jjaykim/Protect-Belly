package com.example.protectbelly.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.protectbelly.databinding.WorkoutListAdapterBinding
import com.example.protectbelly.models.Workout

class WorkoutListAdapter: RecyclerView.Adapter<WorkoutListAdapter.Companion.ItemViewHolder> {

    private val context: Context;
    var workoutList: ArrayList<Workout>;

    constructor(context: Context, workoutList: ArrayList<Workout>) {
        this.context = context
        this.workoutList = workoutList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            WorkoutListAdapterBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currItem: Workout = workoutList[position];
        holder.bind(context, currItem)
    }

    override fun getItemCount(): Int {
        return workoutList.size;
    }

    companion object{
        class ItemViewHolder(binding: WorkoutListAdapterBinding) : ViewHolder(binding.root) {
            var itemBinding: WorkoutListAdapterBinding = binding

            fun bind(context: Context, currItem: Workout) {
                val exerciseListAdapter = ExerciseListAdapter(itemBinding.root.context, currItem.workoutExercises);
                itemBinding.rvExerciseList.adapter = exerciseListAdapter;
                itemBinding.rvExerciseList.layoutManager = LinearLayoutManager(itemBinding.root.context);
                itemBinding.tvWorkoutName.text = currItem.workoutName;

            }
        }


    }


}