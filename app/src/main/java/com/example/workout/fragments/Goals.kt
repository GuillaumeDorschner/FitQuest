package com.example.workout.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.databinding.FragmentSignupBinding
import User;
import UserManagement
import android.widget.Toast
import com.example.workout.databinding.FragmentGoalsBinding
import java.lang.Integer.parseInt

class Goals :Fragment(){
    private var _binding: FragmentGoalsBinding? = null
    private lateinit var userManager: UserManagement
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userManager = UserManagement(requireContext().applicationContext)
        _binding = FragmentGoalsBinding.inflate(inflater, container, false)
        var current = userManager.CurrentUser();
        binding.next.setOnClickListener()
        {
            if (current != null) {
                if (binding.editTextGoal.text.isNotEmpty() && binding.editpoids.text.isNotEmpty() && binding.editpas.text.isNotEmpty() && binding.editTraining.text.isNotEmpty()) {
                    val weight = binding.editpoids.text.toString().toIntOrNull()
                    val steps = binding.editpas.text.toString().toIntOrNull()

                    if (weight != null && steps != null) {
                        current.goals = binding.editTextGoal.text.toString();
                        current.training = binding.editTraining.text.toString();
                        current.poids = weight
                        current.pas = steps
                        userManager.modifyUserByEmail(current)
                        (activity as? MainActivity)?.replaceFragment(Profile())

                    } else {
                        binding.textView6.setText("Weight and Steps should be numbers")
                    }
                } else {
                    binding.textView6.setText("Please fill in all fields")
                }
            }
        }

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}