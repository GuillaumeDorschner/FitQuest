package com.example.workout.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.databinding.FragmentProfileBinding

class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.logoutButton.setOnClickListener {
            // Mettre à jour le statut de connexion dans les préférences partagées
            val sharedPreferences = activity?.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
            sharedPreferences?.edit()?.putBoolean("isLoggedIn", false)?.apply()

            // Rediriger vers le fragment de bienvenue
            (activity as? MainActivity)?.replaceFragment(Welcome())
        }

        return binding.root
    }
}
