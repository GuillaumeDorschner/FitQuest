package com.example.workout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.workout.R
import com.example.workout.databinding.FragmentFavoritesBinding
import com.example.workout.databinding.FragmentNewWorkoutBinding
import okio.IOException
import java.io.BufferedReader
import java.io.InputStreamReader

class Favorites : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }

    private fun loadPrograms() : String{
        val filename = "programs.json"
        return try {
            val inputStream = context?.openFileInput(filename)
            val bufferedReader = BufferedReader(InputStreamReader(inputStream))
            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            bufferedReader.close()
            inputStream?.close()

            stringBuilder.toString().trim()
        } catch (e: IOException) {
            e.printStackTrace()
            ""
        }

    }


}