package com.example.protectbelly.fragments.workouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.protectbelly.adapters.WorkoutListAdapter
import com.example.protectbelly.databinding.FragmentReviewWorkoutRoutineBinding
import com.example.protectbelly.models.Routine
import com.example.protectbelly.models.Workout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "Routine"
private const val ARG_PARAM2 = "WorkoutIndex"

/**
 * A simple [Fragment] subclass.
 * Use the [ReviewWorkoutRoutineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ReviewWorkoutRoutineFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var routine: Routine? = null;
    private var workoutIndex: Int = 0;
    private val auth = FirebaseAuth.getInstance();
    private val db = Firebase.firestore;
    private lateinit var binding: FragmentReviewWorkoutRoutineBinding;

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
        binding = FragmentReviewWorkoutRoutineBinding.inflate(inflater,container,false);
        binding.tvRoutineName2.text = routine?.routineName;
        var workoutListAdapter = WorkoutListAdapter(binding.root.context,
            routine?.workouts as ArrayList<Workout>
        );
        binding.rvWorkoutList.adapter = workoutListAdapter;
        binding.rvWorkoutList.layoutManager = LinearLayoutManager(binding.root.context);
        binding.btSave.setOnClickListener {
            SelectExerciseFragment.exercises.removeAll { true };
//            var query = db.collection("users").document(auth.currentUser?.uid.toString()).collection("routines").whereEqualTo("isActive" , true).get().result.documents[0]
            db.collection("users").document(auth.currentUser?.uid.toString()).collection("routines").add(routine as Routine);
            var action = ReviewWorkoutRoutineFragmentDirections.actionReviewWorkoutRoutineFragmentToWorkoutDashboardFragment();
            container?.findNavController()?.navigate(action);
        }

        binding.btnCancel.setOnClickListener {
            SelectExerciseFragment.exercises.removeAll { true };
            var action = ReviewWorkoutRoutineFragmentDirections.actionReviewWorkoutRoutineFragmentToWorkoutDashboardFragment();
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
         * @return A new instance of fragment ReviewWorkoutRoutineFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ReviewWorkoutRoutineFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}