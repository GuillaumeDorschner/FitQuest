package com.example.workout.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.db.williamchart.view.DonutChartView
import com.db.williamchart.view.HorizontalBarChartView
import com.example.workout.MainActivity
import com.example.workout.R
import com.example.workout.adapter.CardsPopularAdapter
import com.example.workout.data.RandomView

class Home : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)
        val textView4 : TextView = view.findViewById(R.id.textView4)
        val textView5 : TextView = view.findViewById(R.id.textViewWalk)
        val textView6 : TextView = view.findViewById(R.id.caloriesTextView)
        textView4.text = RandomView.donutdatahome.toString()
        textView5.text = RandomView.barchartdatahome.toString()
        textView6.text = RandomView.caloriesdatahome.toString()


        val button = view.findViewById<Button>(R.id.button5)

        button.setOnClickListener {
            (requireActivity() as MainActivity).gotoNewWorkOut()
        }

        val horizontalBarChartData = listOf(
            "pas" to 8000f,
            "pas" to RandomView.barchartdatahome.toFloat()
        )

        val chart = view.findViewById<HorizontalBarChartView>(R.id.chart)
        chart.animation.duration = 1000L
        chart.animate(horizontalBarChartData)

        val donutChartDatalist = listOf(
            RandomView.normalizeddonutdatahome,
            100f - RandomView.normalizeddonutdatahome
        )

        val donutchart = view.findViewById<DonutChartView>(R.id.chartDonut)
        donutchart.donutColors = intArrayOf(
            Color.parseColor("#9FEC00"),
            Color.parseColor("#D9D9D9")
        )
        donutchart.animation.duration = 1000L
        donutchart.animate(donutChartDatalist)

        val adapter = CardsPopularAdapter(requireContext(), RandomView.cardPopularList)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        return view
    }
}