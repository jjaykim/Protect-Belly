package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentCardioStartBinding
import com.example.protectbelly.models.CardioExercise
import com.example.protectbelly.models.Workout
import com.example.protectbelly.models.WorkoutExercise

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Workout"
private const val ARG_PARAM2 = "WorkoutExerciseIndex"
private const val ARG_PARAM3 = "HasFailed"

/**
 * A simple [Fragment] subclass.
 * Use the [CardioStartFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardioStartFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var workout: Workout? = null
    private var workoutExercise: WorkoutExercise? = null
    private var workoutIndex = 0
    private var hasFailed = false
    private lateinit var binding: FragmentCardioStartBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workout = it.getSerializable(ARG_PARAM1) as Workout
            workoutIndex = it.getInt(ARG_PARAM2)
            hasFailed = it.getBoolean(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardioStartBinding.inflate(inflater, container, false);
        workoutExercise = workout!!.workoutExercises[workoutIndex];

        binding.tvWorkoutName.text = (workoutExercise as CardioExercise).name
        binding.tvWeightReps.text = (workoutExercise as CardioExercise).timeGoal.toString()
        binding.tvExerciseWeight.text = (workoutExercise as CardioExercise).distanceGoal.toString()

        binding.btStart.setOnClickListener {
            val action = CardioStartFragmentDirections.actionCardioStartFragmentToCardioInProgress()
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
            action.arguments.putBoolean("HasFailed", hasFailed);
            binding.root.findNavController().navigate(action);
        }

        binding.btSkip.setOnClickListener {
            lateinit var action: NavDirections;
            if(workoutIndex == workout!!.workoutExercises.size - 1) {
                action = CardioStartFragmentDirections.actionCardioStartFragmentToWorkoutDashboardFragment()
                action.arguments.putBoolean("WorkoutComplete", true);
            } else if(workout!!.workoutExercises[workoutIndex +1] is CardioExercise) {
                action = CardioStartFragmentDirections.actionCardioStartFragmentSelf2()

            } else {
                action = CardioStartFragmentDirections.actionCardioStartFragmentToWorkoutSetRoutineFragment()
            }
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", ++workoutIndex);
            action.arguments.putBoolean("HasFailed", hasFailed);
            binding.root.findNavController().navigate(action);
        }
        // Inflate the layout for this fragment
        return binding.root;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardioStartFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardioStartFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}