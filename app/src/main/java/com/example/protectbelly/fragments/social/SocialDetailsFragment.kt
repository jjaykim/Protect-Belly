package com.example.protectbelly.fragments.social

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.protectbelly.MainActivity
import com.example.protectbelly.adapters.GroupListAdapter
import com.example.protectbelly.databinding.FragmentSocialDetailsBinding
import com.example.protectbelly.models.Group
import com.example.protectbelly.models.User
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SocialDetailsFragment : Fragment() {
    private val db = Firebase.firestore;
    private lateinit var binding: FragmentSocialDetailsBinding;
    private lateinit var user: User;
    private lateinit var groupList: ArrayList<Group>;
    private var joinedGroup: ArrayList<Group> = ArrayList<Group>();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        user = MainActivity.currentUser;
        groupList = MainActivity.DB_GROUPS;

        binding = FragmentSocialDetailsBinding.inflate(inflater, container, false);

        binding.userName.text = user.name;
        binding.userGroupNumber.text = user.groups?.size.toString()
        binding.userProfile.setImageResource(user.profilePic)


        if (user.groups?.isEmpty() == true) {
            binding.exploreButtonView.visibility = View.VISIBLE
            binding.recommendGroupListView.visibility = View.GONE
            binding.joinedGroupListView.visibility = View.GONE

            binding.exploreButton.setOnClickListener {
                binding.exploreButtonView.visibility = View.GONE

                val groupListAdapter = GroupListAdapter(binding.root.context,
                    groupList.take(groupList.size) as ArrayList<Group>
                );

                binding.recommendGroupListView.visibility = View.VISIBLE
                binding.recommendGroupList.adapter = groupListAdapter;
                binding.recommendGroupList.layoutManager = LinearLayoutManager(binding.root.context)
            }

        } else {
            binding.exploreButtonView.visibility = View.GONE
            binding.recommendGroupListView.visibility = View.GONE
            binding.joinedGroupListView.visibility = View.VISIBLE

            user.groups!!.forEach { userGroup ->
                groupList.forEach {
                    if (it.documentId == userGroup) {
                        joinedGroup.add(it);
                    }
                }
            }

            val groupListAdapter = GroupListAdapter(binding.root.context,
                joinedGroup.take(joinedGroup.size) as ArrayList<Group>
            );
            binding.joinedGroupList.adapter = groupListAdapter;
            binding.joinedGroupList.layoutManager = LinearLayoutManager(binding.root.context)

        }

        return binding.root;
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        view.findViewById<Button>(R.id.goToGroupScreen).setOnClickListener {
//            findNavController().navigate(R.id.action_socialDetailsFragment_to_groupDetailsFragment)
//        }
    }
}