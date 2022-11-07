package com.example.protectbelly.fragments.workouts

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.protectbelly.databinding.FragmentSelectExerciseBinding
import com.example.protectbelly.models.Exercise
import com.example.protectbelly.models.ExerciseResponseObject
import com.example.protectbelly.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SelectExerciseFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SelectExerciseFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentSelectExerciseBinding;
    private var exercises = ArrayList<Exercise>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectExerciseBinding.inflate(inflater, container, false);
        var api = RetrofitClient.getInstance()?.getApi();


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
                Log.d("ABC",exercises.map { exercise -> exercise.exerciseName }.toString());

                Log.d("ABC", "Exercises: " + exercises.size)
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