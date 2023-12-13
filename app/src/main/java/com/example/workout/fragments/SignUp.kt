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

class SignUp : Fragment() {

    private var _binding: FragmentSignupBinding? = null
    private lateinit var userManager: UserManagement
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        userManager = UserManagement(requireContext().applicationContext)

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        val current = userManager.CurrentUser()
        if(current != null)
        {
            binding.editTextDate.setText(current.dateOfBirth)
            binding.editTextMail.setText(current.email)
            binding.editTextMail.isEnabled = false
            binding.editTextPrenom.setText(current.firstName)
            binding.editTextTextNom.setText(current.lastName)
            binding.editTextTextPassword.setText(current.password)
        }
        binding.next.setOnClickListener{
            val nom = binding.editTextTextNom.text.toString()
            val prenom = binding.editTextPrenom.text.toString()
            val mail = binding.editTextMail.text.toString()
            val date = binding.editTextDate.text.toString()
            val password = binding.editTextTextPassword.text.toString()
            val confirmPassword = binding.editTextTextPassword2.text.toString()
            var notcorrect = true;
            if(nom.isEmpty() || prenom.isEmpty()||mail.isEmpty()||date.isEmpty()||password.isEmpty()||confirmPassword.isEmpty())
            {
                binding.textView6.setText("please enter all informations")
            }
            else if(userManager.GetMails().contains(mail) && current ==null)
            {
                binding.textView6.setText("User already created with this mail");
            }
              else if(password!=confirmPassword) {
                binding.textView6.setText("Passwords not matches, please verify")

            }
            else{
                if (current==null)
                {
                    val newUser = User(nom, prenom, date, mail, password,true)
                    userManager.addUser(newUser)
                    (requireActivity() as MainActivity).replaceFragment(Goals())
                }
                else
                {
                    current.password = password
                    current.firstName = prenom
                    current.lastName = nom
                    current.dateOfBirth = date
                    userManager.modifyUserByEmail(current)
                    (requireActivity() as MainActivity).replaceFragment(Profile())

                }

            }
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
    fun getUserManagementInstance(): UserManagement {
        return userManager
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}