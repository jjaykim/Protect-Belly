package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentWorkoutSetRoutineBinding
import com.example.protectbelly.models.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Workout"
private const val ARG_PARAM2 = "WorkoutExerciseIndex"
private const val ARG_PARAM3 = "Set"
private const val ARG_PARAM4 = "HasFailed"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutSetRoutineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutSetRoutineFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var workout: Workout? = null
    private var workoutExercise: WorkoutExercise? = null
    private var workoutIndex = 0;
    private var set: Int = 1;
    private var hasFailed = false;
    private lateinit var binding: FragmentWorkoutSetRoutineBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workout = it.getSerializable(ARG_PARAM1) as Workout
            workoutIndex = it.getInt(ARG_PARAM2)
            set = it.getInt(ARG_PARAM3)
            hasFailed = it.getBoolean(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        workoutExercise = workout?.workoutExercises?.get(workoutIndex);
        binding = FragmentWorkoutSetRoutineBinding.inflate(inflater, container, false);
        binding.tvWorkoutName.text = workoutExercise?.name;

        binding.tvSetsComplete.text = set.toString();


        if(workoutExercise is WeightExercise) {
            binding.tvExerciseWeight.text = (workoutExercise as WeightExercise)?.weight.toString();
            binding.tvWeightReps.text = (workoutExercise as WeightExercise)?.reps.toString();
            binding.tvAllSets.text = "out of ${(workoutExercise as WeightExercise).sets}"
        } else {
            binding.tvWeightReps.text = (workoutExercise as CalisthenicExercise)?.reps.toString();
            binding.tvAllSets.text = "out of ${(workoutExercise as CalisthenicExercise).sets}"
        }

        binding.btComplete.setOnClickListener {
            changePage();
        }

        binding.btFailed.setOnClickListener {
            hasFailed = true;
            changePage();
        }
        // Inflate the layout for this fragment
        return binding.root;
    }

    private fun changePage() {
        lateinit var action: NavDirections;
        if( (workoutExercise is CalisthenicExercise &&  set == (workoutExercise as CalisthenicExercise).sets) || (workoutExercise is WeightExercise && set == (workoutExercise as WeightExercise).sets)) {

            if(workoutIndex == workout!!.workoutExercises.size - 1) {
                action = WorkoutSetRoutineFragmentDirections.actionWorkoutSetRoutineFragmentToWorkoutDashboardFragment()
                action.arguments.putBoolean("WorkoutComplete", true)
            }
            else if(workout!!.workoutExercises[workoutIndex+1] is CardioExercise) {
                action = WorkoutSetRoutineFragmentDirections.actionWorkoutSetRoutineFragmentToCardioStartFragment()
            } else {
                action = WorkoutSetRoutineFragmentDirections.actionWorkoutSetRoutineFragmentSelf()
            }
            set = 1;
            workoutIndex++
            hasFailed = false

        } else {
            action = WorkoutSetRoutineFragmentDirections.actionWorkoutSetRoutineFragmentToBreakTimer()
            set++;
        }
        action.arguments.putSerializable("Workout", workout);
        action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
        action.arguments.putInt("Set", set);
        action.arguments.putBoolean("HasFailed", hasFailed);
        binding.root.findNavController().navigate(action);
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkoutSetRoutineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkoutSetRoutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}