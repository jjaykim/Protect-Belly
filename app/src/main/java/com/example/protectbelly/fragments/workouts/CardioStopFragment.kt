package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentCardioStopBinding
import com.example.protectbelly.models.CardioExercise
import com.example.protectbelly.models.Workout
import com.example.protectbelly.models.WorkoutExercise

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Workout"
private const val ARG_PARAM2 = "WorkoutExerciseIndex"
private const val ARG_PARAM3 = "HasFailed"
private const val ARG_PARAM4 = "TimeRan"

/**
 * A simple [Fragment] subclass.
 * Use the [CardioStopFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardioStopFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var workout: Workout? = null
    private var workoutExercise: WorkoutExercise? = null
    private var workoutIndex = 0
    private var hasFailed = false
    private var timeRan = 0;
    private lateinit var binding: FragmentCardioStopBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workout = it.getSerializable(ARG_PARAM1) as Workout
            workoutIndex = it.getInt(ARG_PARAM2)
            hasFailed = it.getBoolean(ARG_PARAM3)
            timeRan = it.getInt(ARG_PARAM4)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardioStopBinding.inflate(inflater, container, false);

        binding.tvTime.text = convertIntToTime(timeRan);

        binding.ivResumeCardio.setOnClickListener {
            val action = CardioStopFragmentDirections.actionCardioStopFragmentToCardioInProgress()
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
            action.arguments.putBoolean("HasFailed", hasFailed);
            action.arguments.putInt("TimeRan", timeRan);
            binding.root.findNavController().navigate(action);
        }

        binding.ivStopCardio.setOnClickListener {
            changePage()
        }

        // Inflate the layout for this fragment
        return binding.root;
    }

    private fun changePage() {
        lateinit var action: NavDirections;

        if(workoutIndex == workout!!.workoutExercises.size - 1) {
            action = CardioStopFragmentDirections.actionCardioStopFragmentToWorkoutDashboardFragment()
            action.arguments.putBoolean("WorkoutComplete", true)
        }
        else if(workout!!.workoutExercises[workoutIndex+1] is CardioExercise) {
            action = CardioStopFragmentDirections.actionCardioStopFragmentToCardioStartFragment()
        } else {
            action = CardioStopFragmentDirections.actionCardioStopFragmentToWorkoutSetRoutineFragment()
        }
        workoutIndex++
        hasFailed = false

        action.arguments.putSerializable("Workout", workout);
        action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
        action.arguments.putBoolean("HasFailed", hasFailed);
        binding.root.findNavController().navigate(action);
    }

    private fun convertIntToTime(time: Int): String {
        var minutes = "0";
        var seconds = "";
        if(time >= 60) {
            minutes = (time / 60).toString();
            seconds = if(time%60 < 10) {
                "0${time%60}"
            } else {
                (time%60).toString();
            }
        } else {
            seconds = if(time < 10) {
                "0${time}"
            } else {
                time.toString();
            }

        }
        return "$minutes:$seconds";
     }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardioStopFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardioStopFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}