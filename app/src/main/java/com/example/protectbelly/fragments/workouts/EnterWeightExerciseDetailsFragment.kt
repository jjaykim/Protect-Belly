package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentEnterWeightExerciseDetailsBinding
import com.example.protectbelly.models.CalisthenicExercise
import com.example.protectbelly.models.Routine
import com.example.protectbelly.models.WeightExercise
import com.example.protectbelly.models.WorkoutExercise

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "WorkoutExercise"
private const val ARG_PARAM2 = "Routine"
private const val ARG_PARAM3 = "WorkoutIndex"
private const val ARG_PARAM4 = "IsWeightlifting"

/**
 * A simple [Fragment] subclass.
 * Use the [EnterWeightExerciseDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EnterWeightExerciseDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var workoutExercise: WorkoutExercise? = null
    private var routine: Routine? = null
    private var workoutIndex: Int = 0;
    private var isWeightlifting: Boolean = false;
    private lateinit var binding: FragmentEnterWeightExerciseDetailsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            workoutExercise = it.getSerializable(ARG_PARAM1) as WorkoutExercise?
            routine = it.getSerializable(ARG_PARAM2) as Routine?
            workoutIndex = it.getInt(ARG_PARAM3);
            isWeightlifting = it.getBoolean(ARG_PARAM4);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEnterWeightExerciseDetailsBinding.inflate(inflater,container,false);

        binding.tvExerciseName.text = workoutExercise?.name;

        if(workoutExercise is CalisthenicExercise) {
            binding.tvStartingWeight.visibility = View.GONE
            binding.etStartWeight.visibility = View.GONE
            binding.tvWeightIncrement.visibility = View.GONE
            binding.etWeightIncrement.visibility = View.GONE
            binding.etIncrementFrequency.visibility = View.GONE
            binding.tvIncrementFrequency.visibility = View.GONE
        }

        binding.btSelectExercise.setOnClickListener {
            var action = EnterWeightExerciseDetailsFragmentDirections.actionEnterWeighExerciseDetailsFragmentToSelectExerciseFragment();
            action.arguments.putSerializable("Routine", routine);
            action.arguments.putInt("WorkoutIndex", workoutIndex);
            container?.findNavController()?.navigate(action);
        }

        binding.btWorkoutDetails.setOnClickListener {
            if(validateInput()) {
                if(isWeightlifting) {
                    (workoutExercise as WeightExercise).reps = binding.etReps.text.toString().toInt();
                    (workoutExercise as WeightExercise).sets = binding.etSets.text.toString().toInt();
                    (workoutExercise as WeightExercise).weight = binding.etStartWeight.text.toString().toInt();
                    (workoutExercise as WeightExercise).incrementWeight = binding.etWeightIncrement.text.toString().toInt();
                    (workoutExercise as WeightExercise).incrementFrequency = binding.etIncrementFrequency.text.toString().toInt();
                    routine?.workouts?.last()?.workoutExercises?.add(workoutExercise as WeightExercise);
                } else {
                    (workoutExercise as CalisthenicExercise).reps = binding.etReps.text.toString().toInt();
                    (workoutExercise as CalisthenicExercise).sets = binding.etSets.text.toString().toInt();
                    routine?.workouts?.last()?.workoutExercises?.add(workoutExercise as CalisthenicExercise);
                }

                var action = EnterWeightExerciseDetailsFragmentDirections.actionEnterWeighExerciseDetailsFragmentToAddRoutineWorkoutDetailsFragment();
                action.arguments.putSerializable("Routine", routine);
                action.arguments.putInt("WorkoutIndex", workoutIndex);
                container?.findNavController()?.navigate(action);
            }
        }

        // Inflate the layout for this fragment
        return binding.root;
    }

    private fun validateInput(): Boolean {
        var isValid = true;
        if(binding.etSets.text.isEmpty()) {
            binding.etSets.error = "Please enter number of sets"
            isValid = false;
        }
        if(binding.etReps.text.isEmpty()) {
            binding.etReps.error = "Please enter number of reps"
            isValid = false;
        }

        if(workoutExercise is WeightExercise) {
            if(binding.etStartWeight.text.isEmpty()) {
                binding.etStartWeight.error = "Please enter number of reps"
                isValid = false;
            }
            if(binding.etWeightIncrement.text.isEmpty()) {
                binding.etWeightIncrement.error = "Please enter number of reps"
                isValid = false;
            }
            if(binding.etIncrementFrequency.text.isEmpty()) {
                binding.etIncrementFrequency.error = "Please enter number of reps"
                isValid = false;
            }
        }
        return isValid;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EnterWeighExerciseDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EnterWeightExerciseDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}