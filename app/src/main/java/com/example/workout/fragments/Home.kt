package com.example.workout.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.db.williamchart.view.BarChartView
import com.db.williamchart.view.DonutChartView
import com.db.williamchart.view.HorizontalBarChartView
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.adapter.CardsPopularAdapter
import com.example.workout.model.CardPopular

class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val cardsList = listOf(
            CardPopular("Cbum", R.drawable.cbum, "https://www.instagram.com/cbum/"),
            CardPopular("Tibo Inshape", R.drawable.tibo, "https://www.youtube.com/user/OutLawzFR"),
            CardPopular("Arnold", R.drawable.arnold, "https://fr.wikipedia.org/wiki/Arnold_Schwarzenegger"),
            CardPopular("Mike Mentzers", R.drawable.mike, "https://fr.wikipedia.org/wiki/Mike_Mentzer"),
        )

        val button = view.findViewById<Button>(R.id.button5)


        button.setOnClickListener {
            (requireActivity() as MainActivity).gotoNewWorkOut()
        }

        val chart = view.findViewById<HorizontalBarChartView>(R.id.chart)

        val items = listOf(
            "pas" to 100F,
            "pas" to 60F,
        )

        chart.animation.duration = 1000L
        chart.animate(items)

        val donutchart = view.findViewById<DonutChartView>(R.id.chartDonut)

        val donutSet = listOf(
            70F,
            80F
        )

        donutchart.donutColors = intArrayOf(
            Color.parseColor("#9FEC00"),
            Color.parseColor("#D9D9D9")
        )
        donutchart.animation.duration = 1000L
        donutchart.animate(donutSet)

        val adapter = CardsPopularAdapter(requireContext(), cardsList)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        return view
    }

    private fun replaceFragment(newWorkout: New_Workout) {

    }
}