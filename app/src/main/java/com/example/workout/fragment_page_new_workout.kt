package com.example.workout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.databinding.FragmentPageNewWorkoutBinding
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.model.CurrentOpenIARequest

class fragment_page_new_workout : Fragment() {
    private lateinit var binding: FragmentPageNewWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_page_new_workout, container, false)
        viewModel = ViewModelProvider(this).get(WorkoutViewModel::class.java)
        binding.workoutViewModel = viewModel

        binding.buttonRun.setOnClickListener {
            val userInput = binding.editTextWorkout.text.toString()

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


        return binding.root
    }
}
