package com.example.workout.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.fragments.*
import com.example.workout.databinding.FragmentWelcomeBinding

class Welcome : Fragment() {

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
            Log.d("messge", "djsbfkjsdhfks")
            (requireActivity() as MainActivity).replaceFragment(Login())
        }

        binding.signUpButton.setOnClickListener {
            Log.d("messge", "djsbfkjsdhfdfhsdqkjfhqjksdfjksqdhjshks")
            (requireActivity() as MainActivity).replaceFragment(SignUp())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
