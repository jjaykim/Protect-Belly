package com.example.protectbelly.fragments.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.MainActivity.Companion.currentUser
import com.example.protectbelly.databinding.FragmentEditAccountDetailsBinding
import com.example.protectbelly.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditAccountDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditAccountDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var formUserData: User? = null
    lateinit var binding: FragmentEditAccountDetailsBinding;

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

        val auth = FirebaseAuth.getInstance()
        binding = FragmentEditAccountDetailsBinding.inflate(inflater, container, false);

        binding.btnCancel.setOnClickListener {
            val action = EditAccountDetailsFragmentDirections.actionEditAccountDetailsFragmentToAccountDetailsFragment();
            container?.findNavController()?.navigate(action);
        }

        binding.btnSubmit.setOnClickListener {
            if(CheckAllFields()) {
                if(binding.rbMale.isChecked) {
                    currentUser!!.gender = "Male"
                } else {
                    currentUser!!.gender = "Female"
                }
                currentUser!!.age = binding.etAge.text.toString().toIntOrNull()
                currentUser!!.height = binding.etHeight.text.toString().toIntOrNull()
                currentUser!!.weight = binding.etWeight.text.toString().toIntOrNull()
                currentUser!!.targetCalories = binding.etCaloricIntake.text.toString().toIntOrNull()
                currentUser!!.targetCarbs = binding.etCarbs.text.toString().toIntOrNull()
                currentUser!!.targetProtein = binding.etProtein.text.toString().toIntOrNull()
                currentUser!!.targetFat = binding.etFat.text.toString().toIntOrNull()
                val db = Firebase.firestore;
                var docRef = db.collection("users").document(auth.currentUser?.uid.toString());
                db.runTransaction { transaction ->
                    var snapshot = transaction.get(docRef);
                    transaction.update(docRef, "age", currentUser?.age);
                    transaction.update(docRef, "height", currentUser?.height);
                    transaction.update(docRef, "weight", currentUser?.weight);
                    transaction.update(docRef, "targetCalories", currentUser?.targetCalories);
                    transaction.update(docRef, "targetCarbs", currentUser?.targetCarbs);
                    transaction.update(docRef, "targetProtein", currentUser?.targetProtein);
                    transaction.update(docRef, "targetFat", currentUser?.targetFat);
                }.addOnSuccessListener {
                    Log.d("ABC", "Updated User")
                    val action = EditAccountDetailsFragmentDirections.actionEditAccountDetailsFragmentToAccountDetailsFragment();
                    container?.findNavController()?.navigate(action)
                    Toast.makeText(context, "User Successfully added", Toast.LENGTH_LONG);
                }
                    .addOnFailureListener { Log.d("ABC", "Failed to update user") }

            }
        }

        val view = binding.root;
        // Inflate the layout for this fragment
        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment EditAccountDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            EditAccountDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun CheckAllFields(): Boolean {
        var returnValue = true
        if(!binding.rbMale.isChecked && !binding.rbFemale.isChecked) {
            binding.rbFemale.setError("Please select your gender");
            returnValue = false;
        } else {
            binding.rbFemale.setError(null);
        }
        if(binding.etAge.length() == 0) {
            binding.etAge.setError("Please enter your age");
            returnValue = false;
        }
        if(binding.etHeight.length() == 0) {
            binding.etHeight.setError("Please enter your height");
            returnValue = false;
        }
        if(binding.etWeight.length() == 0) {
            binding.etWeight.setError("Please enter your weight");
            returnValue = false;
        }
        if(binding.etCaloricIntake.length() == 0) {
            binding.etCaloricIntake.setError("Please enter your caloric intake goal");
            returnValue = false;
        }
        if(binding.etCarbs.length() == 0) {
            binding.etCarbs.setError("Please enter your carbohydrate goal");
            returnValue = false;
        }
        if(binding.etProtein.length() == 0) {
            binding.etProtein.setError("Please enter your protein goal");
            returnValue = false;
        }
        if(binding.etFat.length() == 0) {
            binding.etFat.setError("Please enter your fat goal");
            returnValue = false;
        }
        return returnValue;
    }
}