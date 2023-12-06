package com.example.workout.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.db.williamchart.view.BarChartView
import com.db.williamchart.view.HorizontalBarChartView
import com.example.workout.R
import com.example.workout.adapter.CardsPopularAdapter
import com.example.workout.model.CardPopular

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Home.newInstance] factory method to
 * create an instance of this fragment.
 */
class Home : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

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
        val chart = view.findViewById<HorizontalBarChartView>(R.id.chart)

        val items = listOf(
            "pas" to 100F,
            "pas" to 60F,
        )

        chart.show(items)

        val cercle = view.findViewById(R.id.)

        val donut = listOf(
            10F,
            100
        )




        val adapter = CardsPopularAdapter(requireContext(), cardsList)
        view.findViewById<RecyclerView>(R.id.recyclerView).adapter = adapter

        return view
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Home.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Home().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}