package com.example.workout.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.R
import com.example.workout.databinding.FragmentFavoritesBinding
import com.example.workout.databinding.FragmentNewWorkoutBinding
import com.example.workout.model.Program
import com.google.gson.Gson
import okio.IOException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.InputStreamReader

class Favorites : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)

        val fileContent: List<Program> = loadPrograms(requireContext())
        println(fileContent)

        return view
    }

    private fun loadPrograms(context: Context): List<Program> {
        val filePath = "/data/data/com.example.workout/files/programs.json"
        return try {
            val file = File(filePath)
            val bufferedReader = BufferedReader(FileReader(file))
            val stringBuilder = StringBuilder()
            var line: String?

            while (bufferedReader.readLine().also { line = it } != null) {
                stringBuilder.append(line).append("\n")
            }
            bufferedReader.close()

            val jsonContent = stringBuilder.toString().trim()

            // Utilise Gson pour désérialiser le contenu JSON en une liste d'objets Program
            val gson = Gson()
            gson.fromJson(jsonContent, Array<Program>::class.java).toList()

        } catch (e: IOException) {
            e.printStackTrace()
            emptyList()
        }
    }
}