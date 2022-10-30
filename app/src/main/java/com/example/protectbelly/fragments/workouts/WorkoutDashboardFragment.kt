package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.protectbelly.R
import com.example.protectbelly.models.Exercise
import com.example.protectbelly.models.ExerciseResponseObject
import com.example.protectbelly.network.RetrofitClient
import com.google.firebase.auth.FirebaseAuth
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WorkoutDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WorkoutDashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var exercises: ArrayList<Exercise> = ArrayList<Exercise>();

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
        var auth = FirebaseAuth.getInstance();

        var api = RetrofitClient.getInstance()?.getApi();

        if(exercises.isEmpty()) {
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
                }

                override fun onFailure(call: Call<ExerciseResponseObject>, t: Throwable) {
                    Log.d(
                        "ABC",
                        t.message!!
                    )
                }
            })
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_dashboard, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WorkoutDashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            WorkoutDashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}