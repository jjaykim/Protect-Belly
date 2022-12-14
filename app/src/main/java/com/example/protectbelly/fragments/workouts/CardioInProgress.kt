package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentCardioInProgressBinding
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
 * Use the [CardioInProgress.newInstance] factory method to
 * create an instance of this fragment.
 */
class CardioInProgress : Fragment() {
    // TODO: Rename and change types of parameters
    private var workout: Workout? = null
    private var workoutExercise: WorkoutExercise? = null
    private var workoutIndex = 0
    private var hasFailed = false
    private var timeRan = 0;
    private lateinit var binding: FragmentCardioInProgressBinding;

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
        binding = FragmentCardioInProgressBinding.inflate(inflater, container, false);

        startTimer();

        binding.ivStop.setOnClickListener {
            val action = CardioInProgressDirections.actionCardioInProgressToCardioStopFragment()
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
            action.arguments.putBoolean("HasFailed", hasFailed);
            action.arguments.putInt("TimeRan", timeRan);
            binding.root.findNavController().navigate(action);
        }
        // Inflate the layout for this fragment
        return binding.root;
    }

    private fun startTimer() {

        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (true) {
                    var minutes = "0";
                    var seconds = "";
                    if(timeRan >= 60) {
                        minutes = (timeRan / 60).toString();
                        seconds = if(timeRan%60 < 10) {
                            "0${timeRan%60}"
                        } else {
                            (timeRan%60).toString();
                        }
                    } else {
                        seconds = if(timeRan < 10) {
                            "0${timeRan}"
                        } else {
                            timeRan.toString();
                        }

                    }
                    binding.tvTimeRan.text = "$minutes:$seconds"
                    timeRan++
                    handler.postDelayed(this, 1000)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 1000)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CardioInProgress.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CardioInProgress().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}