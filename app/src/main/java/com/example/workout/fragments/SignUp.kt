package com.example.workout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.R
import com.example.workout.databinding.FragmentWelcomeBinding

class SignUp : Fragment() {

    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            // Remplacer par la logique de navigation vers le fragment de connexion
        }

        binding.signUpButton.setOnClickListener {
            // Remplacer par la logique de navigation vers le fragment d'inscription
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
