package com.example.workout.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workout.R
import com.example.workout.databinding.FragmentNewWorkoutBinding
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.model.MessagesItem
import com.example.workout.networking.ApiConfig
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.viewmodel.WorkoutViewModelFactory

class New_Workout : Fragment() {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Initialize ViewModel
        viewModel = ViewModelProvider(this, WorkoutViewModelFactory(ApiConfig.getApiService())).get(WorkoutViewModel::class.java)

        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_workout, container, false)
        binding.workoutViewModel = viewModel

        // Setup button click listener
        binding.searchButton.setOnClickListener {
            val userInput = binding.inputUsr.text.toString()
            val request = CurrentOpenIARequest(
                model = "gpt-4",
                messages = listOf(
                    MessagesItem(role = "system", content = "You are a fitness assistant, skilled in creating personalized workout programs. Create a programme perfectly suited to the user's level {beginner, intermediate, exper}. Choose the number of training days per week. Focus on these body parts/muscles: {user message}. If the target has nothing to do with bodybuilding, reply: I'm afraid I can't help you with that. Let's stay focused on bodybuilding."),
                    MessagesItem(role = "user", content = userInput)
                )
            )
            viewModel.postWorkoutQuery(request)
        }

        // Observe workoutResponse
        viewModel.workoutResponse.observe(viewLifecycleOwner) { response ->

            if (response != null && response.choices?.isNotEmpty() == true) {
                val content = response.choices?.get(0)?.message?.content
                binding.response.text = content
            }

        }

        // Observe errorMessage
        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                binding.response.text = "An error occurred retry"
            }
        }

        return binding.root
    }
}
