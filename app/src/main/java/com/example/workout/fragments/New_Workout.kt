package com.example.workout.fragments

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workout.R
import com.example.workout.databinding.FragmentNewWorkoutBinding
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.model.MessagesItem
import com.example.workout.data.Program
import com.example.workout.networking.ApiConfig
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.viewmodel.WorkoutViewModelFactory
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.IOException


class New_Workout : Fragment() {
    private lateinit var binding: FragmentNewWorkoutBinding
    private lateinit var viewModel: WorkoutViewModel
    private lateinit var sharedPreferences: SharedPreferences



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProvider(requireActivity(), WorkoutViewModelFactory(ApiConfig.getApiService())).get(WorkoutViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_workout, container, false)
        binding.workoutViewModel = viewModel

        viewModel.searchKeyword.observe(viewLifecycleOwner) { keyword ->
            if (!keyword.isNullOrEmpty()) {
                binding.inputUsr.setText(keyword)
            }
        }
        
        sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isLiked = sharedPreferences.getBoolean("isLiked", false)
        updateHeartButton(isLiked)

        binding.searchButton.setOnClickListener {
            sharedPreferences.edit().putBoolean("isLiked", false).apply()

            val userInput = binding.inputUsr.text.toString()

            viewModel.searchKeyword.value = userInput
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
            val currentLikedState = sharedPreferences.getBoolean("isLiked", false)
            val newLikedState = !currentLikedState
            sharedPreferences.edit().putBoolean("isLiked", newLikedState).apply()
            updateHeartButton(newLikedState)

            if(newLikedState){
                val program = binding.response.text.toString()
                val keyword = binding.inputUsr.text.toString()
    
                if (program.contains("An error occurred retry")) {
                    Toast.makeText(requireContext(), "Error in program. Not saved.", Toast.LENGTH_LONG).show()
                } else {
                    val programObject = Program(keyword, program)
                    if (appendAndSaveProgram(requireContext(), programObject)) {
                        Toast.makeText(requireContext(), "Program saved", Toast.LENGTH_LONG).show()
                    } else {
                        Toast.makeText(requireContext(), "Failed to save program", Toast.LENGTH_LONG).show()
                    }
                }
            }else{
                if (removeSavedProgram(requireContext())) {
                    Toast.makeText(requireContext(), "Last program removed", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(requireContext(), "Failed to remove last program", Toast.LENGTH_LONG).show()
                }
            }
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

    private fun updateHeartButton(isLiked: Boolean) {
        if (isLiked) {
            binding.saveButton.setImageResource(R.drawable.ic_heart_filled)
        } else {
            binding.saveButton.setImageResource(R.drawable.ic_heart_outline)
        }
    }

    private fun readExistingPrograms(context: Context): JSONArray {
        val filename = "programs.json"
        val file = File(context.filesDir, filename)
        if (!file.exists()) return JSONArray()

        return try {
            val content = file.readText()
            JSONArray(content)
        } catch (e: Exception) {
            e.printStackTrace()
            JSONArray()  // Return an empty array in case of an error
        }
    }

    private fun appendAndSaveProgram(context: Context, newProgram: Program): Boolean {
        val existingPrograms = readExistingPrograms(context)
        val gson = Gson()
        val programJson = gson.toJson(newProgram)
        existingPrograms.put(JSONObject(programJson))

        val filename = "programs.json"
        return try {
            context.openFileOutput(filename, MODE_PRIVATE).use {
                it.write(existingPrograms.toString().toByteArray())
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }

    private fun removeSavedProgram(context: Context): Boolean {
        val existingPrograms = readExistingPrograms(context)
    
        // Vérifier s'il y a des programmes à enlever
        if (existingPrograms.length() > 0) {
            // Enlever le dernier programme
            existingPrograms.remove(existingPrograms.length() - 1)
        } else {
            // S'il n'y a pas de programmes, retourner true car il n'y a rien à enlever
            return true
        }
    
        val filename = "programs.json"
        return try {
            context.openFileOutput(filename, MODE_PRIVATE).use {
                it.write(existingPrograms.toString().toByteArray())
            }
            true
        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
    
}
