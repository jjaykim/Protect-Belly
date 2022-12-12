package com.example.protectbelly.fragments.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentAccountDetailsBinding
import com.google.firebase.auth.FirebaseAuth

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val auth = FirebaseAuth.getInstance();

    private lateinit var binding: FragmentAccountDetailsBinding;

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

        binding = FragmentAccountDetailsBinding.inflate(inflater, container,false)

        binding.tvUserName.text = auth.currentUser?.displayName;
        binding.tvEmailAddress.text = auth.currentUser?.email;
        binding.tvPhoneNumber.text = auth.currentUser?.phoneNumber;

        // Allows navigation between fragments

        binding.btnEdit.setOnClickListener {
            val action = AccountDetailsFragmentDirections.actionAccountDetailsFragmentToEditAccountDetailsFragment();
            container?.findNavController()?.navigate(action);
        }

        binding.btnLogOut.setOnClickListener {
            FirebaseAuth.getInstance().signOut();
        }

        val view = binding.root;

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AccountDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
