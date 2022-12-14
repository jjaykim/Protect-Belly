package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentStartWorkoutBinding
import com.example.protectbelly.models.CardioExercise
import com.example.protectbelly.models.Routine
import com.example.protectbelly.models.Workout

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Routine"
private const val ARG_PARAM2 = "Workout"

/**
 * A simple [Fragment] subclass.
 * Use the [StartWorkoutFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StartWorkoutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var routine: Routine? = null
    private var workout: Workout? = null
    private lateinit var binding: FragmentStartWorkoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            routine = it.getSerializable(ARG_PARAM1) as Routine?
            workout = it.getSerializable(ARG_PARAM2) as Workout?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartWorkoutBinding.inflate(inflater, container, false)

        binding.tvStartWorkout.text = "Would you like to start ${workout?.workoutName}"

        binding.btStartWorkout.setOnClickListener {
            val action = if(workout?.workoutExercises?.get(0) is CardioExercise) {
                StartWorkoutFragmentDirections.actionStartWorkoutFragmentToCardioStartFragment()
            } else {
                StartWorkoutFragmentDirections.actionStartWorkoutFragmentToWorkoutSetRoutineFragment()
            }
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", 0);
            action.arguments.putBoolean("HasFailed", false);
            action.arguments.putInt("Set", 1);
            container?.findNavController()?.navigate(action);
        }

        binding.btBackToDashboard.setOnClickListener {
            val action = StartWorkoutFragmentDirections.actionStartWorkoutFragmentToWorkoutDashboardFragment()
            container?.findNavController()?.navigate(action);
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StartWorkoutFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StartWorkoutFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}