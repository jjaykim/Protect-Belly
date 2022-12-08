package com.example.protectbelly.fragments.social.group

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.protectbelly.MainActivity
import com.example.protectbelly.R
import com.example.protectbelly.models.Group
import com.example.protectbelly.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM2 = "Group"

/**
 * A simple [Fragment] subclass.
 * Use the [GroupDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GroupDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var group: Group = Group()
    private var user: User = MainActivity.currentUser;
    private val db = Firebase.firestore;
    private val auth = FirebaseAuth.getInstance();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            group = it.getSerializable(ARG_PARAM2) as Group
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_group_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (!user.groups!!.contains(group.documentId)) {
            view.findViewById<LinearLayout>(R.id.joinGroupLayout).visibility = View.VISIBLE
            view.findViewById<TextView>(R.id.leaveGroupText).visibility = View.GONE
        } else {
            view.findViewById<LinearLayout>(R.id.joinGroupLayout).visibility = View.GONE
            view.findViewById<TextView>(R.id.leaveGroupText).visibility = View.VISIBLE
        }

        view.findViewById<Button>(R.id.joinGroupButton).setOnClickListener {
            joinGroup()
        }

        view.findViewById<TextView>(R.id.leaveGroupText).setOnClickListener {
            leaveGroup()
        }


        view.findViewById<TextView>(R.id.pageHeaderTv).text = group.title
        view.findViewById<TextView>(R.id.aboutDetailTv).text = group.description
        view.findViewById<TextView>(R.id.locationDetailTv).text = group.location
        view.findViewById<TextView>(R.id.organizerNameTv).text = group.organizerName


        view.findViewById<Button>(R.id.backSocialDetailBt).setOnClickListener {
            findNavController().navigate(R.id.action_groupDetailsFragment_to_socialDetailsFragment)
        }
    }

    private fun joinGroup() {
        val newGroups: ArrayList<String> = user.groups!!;
        newGroups.add(group.documentId);

        val newUsers: ArrayList<String> = group.users!!;
        newUsers.add(auth.currentUser?.uid.toString());

        db.collection("users").document(auth.currentUser?.uid.toString()).update("groups", newGroups).addOnSuccessListener {
            db.collection("groups").document(group.documentId).update("users", newUsers).addOnSuccessListener {
                findNavController().navigate(R.id.action_groupDetailsFragment_to_socialDetailsFragment)
            }
        }


    }

    private fun leaveGroup() {
        val newGroups = user.groups!!
        newGroups.remove(group.documentId)

        val newUsers = group.users!!
        newUsers.remove(auth.currentUser?.uid.toString())

        db.collection("users").document(auth.currentUser?.uid.toString()).update("groups", newGroups).addOnSuccessListener {
            db.collection("groups").document(group.documentId).update("users", newUsers).addOnSuccessListener {
                findNavController().navigate(R.id.action_groupDetailsFragment_to_socialDetailsFragment)
            }
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment GroupDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            GroupDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}