package com.example.workout.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.ScrollView
import android.widget.Toast
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
import java.io.IOException


class New_Workout : Fragment() {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity(), WorkoutViewModelFactory(ApiConfig.getApiService())).get(WorkoutViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_workout, container, false)
        binding.workoutViewModel = viewModel

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

        viewModel.workoutResponse.observe(viewLifecycleOwner) { response ->

            if (response != null && response.choices?.isNotEmpty() == true) {
                val content = response.choices?.get(0)?.message?.content
                binding.response.text = content
            }

        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                binding.progressLoader.visibility = View.VISIBLE
                binding.response.visibility = View.GONE
                binding.responseCar.visibility = View.GONE
                binding.saveButton.visibility = View.GONE
            }else{
                binding.progressLoader.visibility = View.GONE
                binding.response.visibility = View.VISIBLE
                binding.responseCar.visibility = View.VISIBLE
                binding.saveButton.visibility = View.VISIBLE
            }
        }

        viewModel.errorMessage.observe(viewLifecycleOwner) { error ->
            if (error != null) {
                binding.response.text = "An error occurred retry"
            }
        }

        binding.saveButton.setOnClickListener {
            Toast.makeText(requireContext(), "Program saved", Toast.LENGTH_LONG).show()
            saveProgram()
        }

        // val parentLayout: ScrollView? = view?.findViewById(R.id.new_workout)
        // parentLayout?.setOnTouchListener { _, event ->
        //     if (event.action == MotionEvent.ACTION_DOWN) {
        //         hideKeyboard()
        //     }
        //     false
        // }

        return binding.root
    }

    // private fun hideKeyboard() {
    //     val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    //     imm.hideSoftInputFromWindow(view?.windowToken, 0)
    // }

    private fun saveProgram(): Boolean {
        val program = binding.response.text.toString()
        val keyword= binding.inputUsr.text.toString()
        val filename = "programs.json"

        if (program.isBlank()) {
            println("An empty program cannot be stored")
            return false
        }

        val dataToSave = "{\"$keyword\": \"$program\"}"
        return try {
            context?.openFileOutput(filename, Context.MODE_APPEND).use {
                it?.write("$dataToSave\n".toByteArray())
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}
