package com.example.workout.fragments

import UserManagement
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
    private lateinit var userManager: UserManagement

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Initialize UserManagement with context when the fragment is attached
        userManager = UserManagement(requireContext().applicationContext)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val current = userManager.CurrentUser()
        if(current != null)
        {
            binding = FragmentProfileBinding.inflate(inflater, container, false)
            binding.Name.setText(""+current.firstName+ " "+current.lastName)
            binding.About.text1.text = binding.About.text1.text.toString()+current.dateOfBirth
            binding.About.text2.setText(current.email)
            binding.About.text3.setText("*******")
            binding.Goals.Goals.setText(current.goals)
            binding.Goals.pas.setText(""+current.pas)
            binding.Goals.Poids.setText(""+current.poids)
            binding.Goals.training.setText(current.training)
        }
        binding.About.searchButton.setOnClickListener{
            (activity as? MainActivity)?.replaceFragment(SignUp())
        }
        binding.Goals.searchButton.setOnClickListener {
            (activity as? MainActivity)?.replaceFragment(Goals())
        }
        binding.logoutButton.setOnClickListener {
            userManager.logout()
            (activity as? MainActivity)?.replaceFragment(Welcome())
        }

        return binding.root
    }
}
