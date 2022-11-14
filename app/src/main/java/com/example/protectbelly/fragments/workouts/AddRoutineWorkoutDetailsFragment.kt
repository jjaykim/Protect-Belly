package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.protectbelly.adapters.ExerciseDetailsAdapter
import com.example.protectbelly.databinding.FragmentAddRoutineWorkoutDetailsBinding
import com.example.protectbelly.models.Routine

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Routine"
private const val ARG_PARAM2 = "WorkoutIndex"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRoutineWorkoutDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRoutineWorkoutDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var routine: Routine? = null
    private var workoutIndex: Int = 0;
    private lateinit var binding: FragmentAddRoutineWorkoutDetailsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            routine = it.getSerializable(ARG_PARAM1) as Routine?;
            workoutIndex = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddRoutineWorkoutDetailsBinding.inflate(inflater, container,false);

        Log.d("ABC", routine.toString());
        var exerciseDetailAdapter = ExerciseDetailsAdapter(binding.root.context, routine?.workouts!![workoutIndex].workoutExercises);
        binding.rvExerciseDetails.adapter = exerciseDetailAdapter;
        binding.rvExerciseDetails.layoutManager = LinearLayoutManager(binding.root.context);

        binding.btAddExercise.setOnClickListener {
            var action = AddRoutineWorkoutDetailsFragmentDirections.actionAddRoutineWorkoutDetailsFragmentToSelectExerciseFragment()
            action.arguments.putSerializable("Routine", routine);
            action.arguments.putInt("WorkoutIndex", workoutIndex);
            container?.findNavController()?.navigate(action);
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
         * @return A new instance of fragment AddRoutineWorkoutDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddRoutineWorkoutDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}