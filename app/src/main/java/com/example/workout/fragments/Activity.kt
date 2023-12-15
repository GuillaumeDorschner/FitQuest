import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.db.williamchart.view.BarChartView
import com.db.williamchart.view.DonutChartView
import com.db.williamchart.view.LineChartView
import com.example.workout.R
import com.example.workout.model.RandomView


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
        val textView5 : TextView = view.findViewById(R.id.textView5)
        val textView3 : TextView = view.findViewById(R.id.textView3)
        val textView1 : TextView = view.findViewById(R.id.textView1)
        val dayText1 : TextView = view.findViewById(R.id.Highestday)
        val dayText2 : TextView = view.findViewById(R.id.LowestDay)
        val chartDonut1 = view.findViewById<DonutChartView>(R.id.chartDonut1)
        val chartDonut2 = view.findViewById<DonutChartView>(R.id.chartDonut2)
        val chartDonut3 = view.findViewById<DonutChartView>(R.id.chartDonut3)



        // maximum value for full bar height.
        val maxTrainingTime = 2f // 2 hours
        val averageCalories = 2700f
        val maxsteps = 56000f
        val stepsday = 8000f


        // Normalize the data to the maximum value for the bar height to represent it properly.
        val normalizedChartData = RandomView.trainingData.map { (day, hours) ->
            Pair(day, hours / maxTrainingTime) // Normalize the hours to the scale of 0.0 - 1.0 based on the maxTrainingTime.
        }

        // setting up text views
        textView1.text = RandomView.donutData1.toString()
        textView3.text = RandomView.donutData2.toString()
        textView5.text = RandomView.donutData3.toString()

        // setting up two random days
        dayText1.text = RandomView.randomDay1
        dayText2.text= RandomView.randomDay2


        // Configurez et animez chaque DonutChart
        val colors = intArrayOf(Color.parseColor("#9FEC00"), Color.parseColor("#D9D9D9"))
        val normalizedDonutData1 = (RandomView.donutData1 / maxsteps) * 100
        val normalizedDonutData2 = (RandomView.donutData2 / stepsday) * 100
        val normalizedDonutData3 = (RandomView.donutData3 / stepsday) * 100
        chartDonut1.donutColors = colors
        chartDonut1.animation.duration = 1000L
        chartDonut1.animate(listOf(normalizedDonutData1, 100f - normalizedDonutData1))

        chartDonut2.donutColors = colors
        chartDonut2.animation.duration = 1000L
        chartDonut2.animate(listOf(normalizedDonutData2, 100f - normalizedDonutData2))

        chartDonut3.donutColors = colors
        chartDonut3.animation.duration = 1000L
        chartDonut3.animate(listOf(normalizedDonutData3, 100f - normalizedDonutData3))

        // Show the normalized chart data.
        chart.animation.duration = 1000L
        chart.animate(normalizedChartData)
        lineChart.animation.duration = 1000L
        lineChart.animate(RandomView.caloricData)

        return view
    }
}