package com.example.protectbelly.fragments.social

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.protectbelly.MainActivity
import com.example.protectbelly.adapters.GroupListAdapter
import com.example.protectbelly.databinding.FragmentSocialDetailsBinding
import com.example.protectbelly.fragments.social.SocialDetailsFragmentDirections.*
import com.example.protectbelly.models.Group
import com.example.protectbelly.models.User

class SocialDetailsFragment : Fragment() {
    private lateinit var binding: FragmentSocialDetailsBinding;
    private lateinit var user: User;
    private lateinit var groupList: ArrayList<Group>;


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

        binding.createGroupButton.setOnClickListener {
            val action = actionSocialDetailsFragmentToCreateGroupFragment()
            container?.findNavController()?.navigate(action);
        }


        if (user.groups?.isEmpty() == true) {
            binding.exploreButtonView.visibility = View.VISIBLE
            binding.recommendGroupListView.visibility = View.GONE
            binding.joinedGroupListView.visibility = View.GONE
            binding.otherGroupListView.visibility = View.GONE
            binding.recommendButtonView.visibility = View.GONE

            binding.exploreButton.setOnClickListener {
                binding.exploreButtonView.visibility = View.GONE

                val groupListAdapter = GroupListAdapter(binding.root.context,
                    groupList
                );

                binding.recommendGroupListView.visibility = View.VISIBLE
                binding.recommendGroupList.adapter = groupListAdapter;
                binding.recommendGroupList.layoutManager = LinearLayoutManager(binding.root.context)
            }

        } else {
            binding.exploreButtonView.visibility = View.GONE
            binding.recommendGroupListView.visibility = View.GONE
            binding.joinedGroupListView.visibility = View.VISIBLE
            binding.otherGroupListView.visibility = View.GONE
            binding.recommendButtonView.visibility = View.VISIBLE


            var isRecommendBtnClicked = false;
            var userGroups: ArrayList<Group> = ArrayList();
            var recommendGroups: ArrayList<Group> = ArrayList()

            binding.recommendButton.setOnClickListener {
                isRecommendBtnClicked = !isRecommendBtnClicked

                if (isRecommendBtnClicked) {
                    binding.recommendButton.text = "Go to the Joined Groups"
                    binding.joinedGroupListView.visibility = View.GONE
                    binding.otherGroupListView.visibility = View.VISIBLE


                } else {
                    binding.recommendButton.text = "Go to the Recommended Group"
                    binding.otherGroupListView.visibility = View.GONE
                    binding.joinedGroupListView.visibility = View.VISIBLE
                }
            }

            // Joined Group
            user.groups!!.forEach { userGroup ->
                groupList.forEach {
                    if (it.documentId == userGroup) {
                        userGroups.add(it);
                    }
                }
            }
            val groupListAdapter = GroupListAdapter(binding.root.context,
                userGroups
            );
            binding.joinedGroupList.adapter = groupListAdapter;
            binding.joinedGroupList.layoutManager = LinearLayoutManager(binding.root.context)

            groupList.forEach {
                if (!user.groups!!.contains(it.documentId)) {
                    recommendGroups.add(it)
                }
            }

            val recommendGroupListAdapter = GroupListAdapter(binding.root.context,
                recommendGroups
            );
            binding.otherGroupList.adapter = recommendGroupListAdapter
            binding.otherGroupList.layoutManager = LinearLayoutManager(binding.root.context)
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