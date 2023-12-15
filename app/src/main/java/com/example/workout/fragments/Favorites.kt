package com.example.workout.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.R
import com.example.workout.databinding.FragmentFavoritesBinding
import com.example.workout.data.Program
import com.example.workout.adapter.ProgramAdapter
import com.google.gson.Gson
import okio.IOException
import java.io.BufferedReader
import java.io.File
import java.io.FileReader
import java.io.FileWriter

class Favorites : Fragment() {
    private lateinit var binding: FragmentFavoritesBinding
    private lateinit var programAdapter: ProgramAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_favorites, container, false)
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val fileContent: MutableList<Program> = loadPrograms(requireContext())

        programAdapter = ProgramAdapter(requireContext(), fileContent, object : ProgramAdapter.OnRemoveButtonClickListener {
            override fun onRemoveButtonClick(program: Program) {
                // Supprime le programme de la liste
                fileContent.remove(program)

                // Réécrit la liste mise à jour dans le fichier
                saveProgramsToFile(fileContent)

                // Rafraîchit l'adaptateur pour refléter les changements
                programAdapter.notifyDataSetChanged()
            }
        })

        recyclerView.adapter = programAdapter

        val searchEditText: EditText = view.findViewById(R.id.inputUsr)
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Ne rien faire ici
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Filtrer la liste lorsque le texte de recherche change
                programAdapter.filter(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {
                // Ne rien faire ici
            }
        })

        return view
    }


    private fun loadPrograms(context: Context): MutableList<Program> {
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
            gson.fromJson(jsonContent, Array<Program>::class.java).toMutableList()

        } catch (e: IOException) {
            e.printStackTrace()
            mutableListOf()
        }
    }

    private fun saveProgramsToFile(programs: List<Program>) {
        val filePath = "/data/data/com.example.workout/files/programs.json"
        try {
            val file = File(filePath)
            val fileWriter = FileWriter(file)

            val gson = Gson()
            val jsonString = gson.toJson(programs)

            fileWriter.write(jsonString)
            fileWriter.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}