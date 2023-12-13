package com.example.workout.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.databinding.FragmentLoginBinding

class Login : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener{
            val sharedPreferences = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putBoolean("isLoggedIn", true)?.apply()

            (requireActivity() as MainActivity).replaceFragment(Home())
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}