package com.example.protectbelly.fragments.social.group

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.protectbelly.MainActivity
import com.example.protectbelly.MainActivity.Companion.DB_GROUPS
import com.example.protectbelly.R
import com.example.protectbelly.databinding.FragmentCreateGroupBinding
import com.example.protectbelly.models.Group
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CreateGroupFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CreateGroupFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentCreateGroupBinding;
    private val db = Firebase.firestore;
    private val auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val auth = FirebaseAuth.getInstance()
        binding = FragmentCreateGroupBinding.inflate(inflater, container, false)

        binding.leaveCreateSocialPageBtn.setOnClickListener {
            val action = CreateGroupFragmentDirections.actionCreateGroupFragmentToSocialDetailsFragment();
            container?.findNavController()?.navigate(action);
        }

        binding.createSocialBtn.setOnClickListener {
            if(CheckAllFields()) {
                val tempGroup = Group();

                val formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
                val current = LocalDateTime.now().format(formatter)
                val curUser = MainActivity.currentUser

                tempGroup.title = binding.socialTitleEt.text.toString()
                tempGroup.description = binding.socialDescriptionEt.text.toString()
                tempGroup.type = binding.socialTypeEt.text.toString()
                tempGroup.location = binding.socialLocationEt.text.toString()
                tempGroup.users = ArrayList<String>()
                tempGroup.organizerName = curUser.name.toString()
                tempGroup.organizerId = auth.currentUser?.uid.toString()
                tempGroup.createdAt = current.toString()


                db.collection("groups").add(tempGroup).addOnSuccessListener {
                    val newGroups = curUser.groups
                    newGroups?.add(it.id)
                    tempGroup.logo = R.drawable.ic_random_group_logo;
                    tempGroup.documentId = it.id

                    db.collection("users").document(auth.currentUser?.uid.toString()).update("groups", newGroups).addOnSuccessListener {
                        MainActivity.DB_GROUPS.add(tempGroup)

                        findNavController().navigate(R.id.action_createGroupFragment_to_socialDetailsFragment)
                    }




                }
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
         * @return A new instance of fragment CreateGroupFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CreateGroupFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun CheckAllFields(): Boolean {
        var returnValue = true

        if(binding.socialTitleEt.length() == 0) {
            binding.socialTitleEt.error = ("Please enter the new group title");
            returnValue = false;
        }
        if(binding.socialTypeEt.length() == 0) {
            binding.socialTypeEt.error =("Please enter the new group type");
            returnValue = false;
        }
        if(binding.socialDescriptionEt.length() == 0) {
            binding.socialDescriptionEt.error = ("Please enter the new description");
            returnValue = false;
        }
        if(binding.socialLocationEt.length() == 0) {
            binding.socialLocationEt.error = ("Please enter the new group location");
            returnValue = false;
        }

        return returnValue;
    }
}