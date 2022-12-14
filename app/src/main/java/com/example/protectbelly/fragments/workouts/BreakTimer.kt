package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentBreakTimerBinding
import com.example.protectbelly.models.Workout
import com.example.protectbelly.models.WorkoutExercise

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Workout"
private const val ARG_PARAM2 = "WorkoutExerciseIndex"
private const val ARG_PARAM3 = "Set"
private const val ARG_PARAM4 = "HasFailed"

/**
 * A simple [Fragment] subclass.
 * Use the [BreakTimer.newInstance] factory method to
 * create an instance of this fragment.
 */
class BreakTimer : Fragment() {
    // TODO: Rename and change types of parameters
    private var workout: Workout? = null
    private var workoutExercise: WorkoutExercise? = null
    private var workoutIndex = 0;
    private var set: Int = 0;
    private var hasFailed = false;
    private lateinit var binding: FragmentBreakTimerBinding;
    private var i = 0;

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
        binding = FragmentBreakTimerBinding.inflate(inflater, container, false);
        binding.progressBar3.progress = 0;
        binding.progressBar3.max = 180
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                // set the limitations for the numeric
                // text under the progress bar
                if (true) {
                    var minutes = "0";
                    var seconds = "";
                    if(i >= 60) {
                        minutes = (i / 60).toString();
                        seconds = if(i%60 < 10) {
                            "0${i%60}"
                        } else {
                            (i%60).toString();
                        }
                    } else {
                        seconds = if(i < 10) {
                            "0${i}"
                        } else {
                            i.toString();
                        }

                    }
                    binding.tvMilesCount.text = "$minutes:$seconds"
                    binding.progressBar3.progress = i;
                    i++
                    handler.postDelayed(this, 1000)
                } else {
                    handler.removeCallbacks(this)
                }
            }
        }, 1000)

//        binding.progressBar3.incrementProgressBy(1)

        binding.btNext.setOnClickListener {
            val action = BreakTimerDirections.actionBreakTimerToWorkoutSetRoutineFragment2()
            action.arguments.putSerializable("Workout", workout);
            action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
            action.arguments.putBoolean("HasFailed", hasFailed);
            action.arguments.putInt("Set", set);
            binding.root.findNavController().navigate(action);
        }

        binding.btGiveUp.setOnClickListener {
            lateinit var action: NavDirections;
            if(workoutIndex == workout!!.workoutExercises.size - 1) {
                action = BreakTimerDirections.actionBreakTimerToWorkoutDashboardFragment()
                action.arguments.putBoolean("WorkoutComplete", true);
            } else {
                action = BreakTimerDirections.actionBreakTimerToWorkoutSetRoutineFragment2()
                action.arguments.putSerializable("Workout", workout);
                action.arguments.putInt("WorkoutExerciseIndex", workoutIndex);
                action.arguments.putBoolean("HasFailed", hasFailed);
                action.arguments.putInt("Set", set);
            }


            binding.root.findNavController().navigate(action);
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
         * @return A new instance of fragment BreakTimer.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BreakTimer().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}