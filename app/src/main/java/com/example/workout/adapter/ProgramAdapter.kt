package com.example.workout.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.workout.R
import com.example.workout.data.Program

class ProgramAdapter(private val context: Context,
                     private var programs: MutableList<Program>,
                     private val onRemoveButtonClickListener: OnRemoveButtonClickListener
) :

    RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {
    private var filteredPrograms: List<Program> = programs

    interface OnRemoveButtonClickListener {
        fun onRemoveButtonClick(program: Program)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val keywordTextView: TextView = view.findViewById(R.id.keyword)
        val programTextView: TextView = view.findViewById(R.id.program)
        val removeButton: ImageButton = view.findViewById(R.id.removeButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.card_workout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val program = filteredPrograms[position] // Utilisez filteredPrograms ici
        holder.keywordTextView.text = program.keyword.uppercase()
        holder.programTextView.text = program.program

        holder.removeButton.setOnClickListener {
            onRemoveButtonClickListener.onRemoveButtonClick(program)
        }

    }

    override fun getItemCount(): Int {
        return filteredPrograms.size // Utilisez filteredPrograms.size ici
    }

    fun filter(keyword: String) {
        filteredPrograms = if (keyword.isEmpty()) {
            programs // Si le mot-clé est vide, afficher la liste complète
        }
        else {
            programs.filter { program ->
                program.keyword.contains(keyword, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }

}