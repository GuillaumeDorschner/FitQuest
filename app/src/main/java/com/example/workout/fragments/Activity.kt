import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.db.williamchart.view.BarChartView
import com.db.williamchart.view.LineChartView
import com.example.workout.R


class Activity : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_activity, container, false)

        val chart: BarChartView = view.findViewById(R.id.chartBar)
        val lineChart: LineChartView = view.findViewById(R.id.lineChartView)

        // maximum value for full bar height.
        val maxTrainingTime = 2f // 2 hours
        val averageCalories = 2700f

        // actual training data in hours.
        val trainingData = listOf(
            Pair("Mon", 1.5f), // 1.5 hours of training on Monday
            Pair("Tue", 2f),   // 2 hours (maximum) on Tuesday
            Pair("Wed", 0.5f), // 0.5 hours on Wednesday, and so on...
            Pair("Thu", 1f),
            Pair("Fri", 2f),
            Pair("Sat", 0f),
            Pair("Sun", 1.5f)
        )

        val caloricData = listOf(
            Pair("Mon", 2500f), // Caloric intake for Monday
            Pair("Tue", 3000f), // Caloric intake for Tuesday, and so on...
            Pair("Wed", 2600f),
            Pair("Thu", 1800f),
            Pair("Fri", 3200f),
            Pair("Sat", 2700f),
            Pair("Sun", 2200f)
        )

        // Normalize the data to the maximum value for the bar height to represent it properly.
        val normalizedChartData = trainingData.map { (day, hours) ->
            Pair(day, hours / maxTrainingTime) // Normalize the hours to the scale of 0.0 - 1.0 based on the maxTrainingTime.
        }



        // Show the normalized chart data.
        chart.show(normalizedChartData)
        lineChart.show(caloricData)

        return view
    }
}