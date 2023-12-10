package com.example.workout.fragments

import User
import UserManagement
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.databinding.FragmentSignupBinding

class SignUp : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    val userManager = UserManagement(requireContext())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignupBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener{
            // Mise à jour des préférences partagées
            val sharedPreferences = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putBoolean("isLoggedIn", true)?.apply()

            // Naviguer vers le fragment Home
            (requireActivity() as MainActivity).replaceFragment(Home())
        }

        return binding.root
    }
    private fun onContinueClicked(view: View) {
        val nomEditText = view.findViewById<EditText>(R.id.editTextTextNom)
        val prenomEditText = view.findViewById<EditText>(R.id.editTextPrenom)
        val mailEditText = view.findViewById<EditText>(R.id.editTextMail)
        val dateEditText = view.findViewById<EditText>(R.id.editTextDate)
        val passwordEditText = view.findViewById<EditText>(R.id.editTextTextPassword)
        val confirmPasswordEditText = view.findViewById<EditText>(R.id.editTextTextPassword2)

        val nom = nomEditText.text.toString()
        val prenom = prenomEditText.text.toString()
        val mail = mailEditText.text.toString()
        val date = dateEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        if (password == confirmPassword) {
            val newUser = User(nom, prenom, date, mail, password)
            userManager.addUser(newUser)
            // Add logic for redirection to another fragment or activity after successful signup
        } else {
            // Handle case where passwords do not match
            Toast.makeText(requireContext(), "Passwords do not match", Toast.LENGTH_SHORT).show()
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
