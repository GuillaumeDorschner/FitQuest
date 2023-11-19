package com.example.workout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.databinding.DataBindingUtil
import com.example.workout.R
import com.example.workout.databinding.FragmentNewWorkoutBinding
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.model.MessagesItem

class New_Workout : Fragment() {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_workout, container, false)
        viewModel = ViewModelProvider(this)[WorkoutViewModel::class.java]
        binding.workoutViewModel = viewModel

        binding.searchIcon.setOnClickListener {
            val userInput = binding.inputUsr.text.toString()

            val systemMessage = MessagesItem(
                role = "system",
                content = "You are a fitness assistant, skilled in creating personalized workout programs. " +
                        "Create a programme perfectly suited to the user's level {beginner, intermediate, expert}. " +
                        "Choose the number of training days per week. Focus on these body parts/muscles: {user message}. " +
                        "If the target has nothing to do with bodybuilding, reply: I'm afraid I can't help you with that. " +
                        "Let's stay focused on bodybuilding."
            )

            val requestData = CurrentOpenIARequest(
                model = "gpt-4",
                messages = listOf(systemMessage, MessagesItem(role = "user", content = userInput))
            )

            viewModel.postWorkoutQuery(requestData)
        }

        setupObservers()

        return binding.root
    }

    private fun setupObservers() {
        viewModel.workoutResponse.observe(viewLifecycleOwner, { response ->
            val content = response?.choices?.firstOrNull()?.message?.content
            binding.response.text = content
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, { error ->
            // Handle the error message here, e.g., display a Toast
        })
    }
}