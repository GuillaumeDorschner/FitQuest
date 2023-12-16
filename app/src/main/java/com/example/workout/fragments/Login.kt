package com.example.workout.fragments

import UserManagement
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
    private lateinit var userManager: UserManagement


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Initialize UserManagement with context when the fragment is attached
        userManager = UserManagement(requireContext().applicationContext)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val userManager = UserManagement(activity)
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        binding.next.setOnClickListener{
            val mail = binding.editTextTextEmailAddress.text.toString();
            val password = binding.editTextTextPassword.text.toString();
            var foundUser = userManager.GetUser(mail,password);
            if(foundUser!=null)
            {
                (requireActivity() as MainActivity).gotoHome()
            }
            else
            {

            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
