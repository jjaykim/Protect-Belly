package com.example.protectbelly.fragments.workouts

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.protectbelly.databinding.FragmentAddRoutineEnterDetailsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [AddRoutineEnterDetailsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddRoutineEnterDetailsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var binding: FragmentAddRoutineEnterDetailsBinding;

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
        binding = FragmentAddRoutineEnterDetailsBinding.inflate(inflater, container, false);
        binding.btBack.setOnClickListener {
            val action =  AddRoutineEnterDetailsFragmentDirections.actionAddRoutineEnterDetailsFragmentToAddRoutineUseTemplateFragment();
            container?.findNavController()?.navigate(action);
        }

        binding.btNext.setOnClickListener {
            if(validateForm()) {
                val action = AddRoutineEnterDetailsFragmentDirections.actionAddRoutineEnterDetailsFragmentToSelectExerciseFragment();
                container?.findNavController()?.navigate(action);
            }
        }

        var variations = ArrayList<String>();
        variations.add("Select Variations")
        for (i in 1..7) {
            variations.add(i.toString());
        }



        var adapter = ArrayAdapter<String>(context as Context, android.R.layout.simple_spinner_dropdown_item, variations);
        binding.spVariations.adapter = adapter;

        // Inflate the layout for this fragment
        return binding.root;
    }

    private fun validateForm(): Boolean {
        var isValid = true;
        if(binding.etRoutineName.length() == 0) {
            isValid = false;
            binding.etRoutineName.error = ("Please enter a Routine name");
        }
        if(binding.spVariations.selectedItem == "Select Variations") {
            binding.tvVariationsError.setTextColor(Color.RED);
            binding.tvVariationsError.text = "Please select variations";
            binding.tvVariationsError.visibility = View.VISIBLE;
        } else {
            binding.tvVariationsError.visibility = View.GONE;
        }
        return isValid;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AddRoutineEnterDetailsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddRoutineEnterDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}