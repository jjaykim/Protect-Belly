package com.example.protectbelly.fragments.workouts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentSelectExerciseBinding
import com.example.protectbelly.models.*
import com.example.protectbelly.models.enums.ExerciseType
import com.example.protectbelly.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Routine"
private const val ARG_PARAM2 = "WorkoutIndex"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectExerciseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var routine: Routine? = null;
    private var workoutIndex: Int = 0;
    private lateinit var binding: FragmentSelectExerciseBinding;
    private var exercises = ArrayList<Exercise>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            routine = it.getSerializable(ARG_PARAM1) as Routine?
            workoutIndex = it.getInt(ARG_PARAM2);
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectExerciseBinding.inflate(inflater, container, false);
        var api = RetrofitClient.getInstance()?.getApi();

        if(routine?.workouts?.size == 0) {
            routine?.workouts?.add(Workout("Workout A", ArrayList<WorkoutExercise>()));
        }

        val request = api?.getAllExercises();

        request?.enqueue(object : Callback<ExerciseResponseObject> {
            override fun onResponse(
                call: Call<ExerciseResponseObject?>,
                response: Response<ExerciseResponseObject?>
            ) {
                if (!response.isSuccessful) {
                    Log.d(
                        "ABC",
                        "Error from API with response code: " + response.code()
                    )
                    return
                }
                val obj: ExerciseResponseObject? = response.body()

                exercises.addAll(obj?.results!!)

                var adapter = ArrayAdapter<String>(context as Context, android.R.layout.select_dialog_singlechoice, exercises.map { exercise -> exercise.exerciseName });
                binding.actvExerciseName.threshold = 1;
                binding.actvExerciseName.setAdapter(adapter);

            }

            override fun onFailure(call: Call<ExerciseResponseObject>, t: Throwable) {
                Log.d(
                    "ABC",
                    t.message!!
                )
            }
        })

        binding.btBackRoutineDetails.setOnClickListener {
            val action = SelectExerciseFragmentDirections.actionSelectExerciseFragmentToAddRoutineEnterDetailsFragment();
            action.arguments.putSerializable("Routine", routine);
            action.arguments.putSerializable("WorkoutIndex", workoutIndex);
            container?.findNavController()?.navigate(action);
        }

        binding.btNextExerciseDetails.setOnClickListener {
            if(binding.actvExerciseName.text.toString().isNotEmpty()) {
                val request2 = api?.getExerciseByName(binding.actvExerciseName.text.toString());
                request2?.enqueue(object : Callback<ExerciseResponseObject> {
                    override fun onResponse(
                        call: Call<ExerciseResponseObject?>,
                        response: Response<ExerciseResponseObject?>
                    ) {
                        if (!response.isSuccessful) {
                            Log.d(
                                "ABC",
                                "Error from API with response code: " + response.code()
                            )
                            return
                        }
                        val obj: ExerciseResponseObject? = response.body()

                        if(obj?.results!!.size > 0) {
                            var addedExercise = obj?.results!![0];
                            lateinit var action: NavDirections;
                            lateinit var addedWorkoutExercise: WorkoutExercise;

                            if(addedExercise.exerciseType() == ExerciseType.CARDIO){
                                addedWorkoutExercise = CardioExercise();
                                addedWorkoutExercise.name = addedExercise.exerciseName;
                                action = SelectExerciseFragmentDirections.actionSelectExerciseFragmentToEnterCardioExerciseDetailsFragment();

                            } else if(addedExercise.exerciseType() == ExerciseType.CALISTHENICS){
                                addedWorkoutExercise = CalisthenicExercise();
                                addedWorkoutExercise.name = addedExercise.exerciseName;
                                action = SelectExerciseFragmentDirections.actionSelectExerciseFragmentToEnterWeighExerciseDetailsFragment();

                            } else if(addedExercise.exerciseType() == ExerciseType.WEIGHTLIFITING) {
                                addedWorkoutExercise = WeightExercise();
                                addedWorkoutExercise.name = addedExercise.exerciseName;
                                action = SelectExerciseFragmentDirections.actionSelectExerciseFragmentToEnterWeighExerciseDetailsFragment()
                                action.arguments.putBoolean("IsWeightlifting", true);
                            }

                            action.arguments.putSerializable("WorkoutExercise", addedWorkoutExercise);
                            action.arguments.putSerializable("Routine", routine);
                            action.arguments.putInt("WorkoutIndex", workoutIndex);
                            container?.findNavController()?.navigate(action);

                        } else {
                            binding.actvExerciseName.error = "Please select a valid exercise"
                        }
                    }

                    override fun onFailure(call: Call<ExerciseResponseObject>, t: Throwable) {

                    }
                })
            } else {
                binding.actvExerciseName.error = "Please select a valid exercise"
            }
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
         * @return A new instance of fragment SelectExerciseFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SelectExerciseFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}