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

        // setting up text views
        textView1.text = RandomView.donutData1.toString()
        textView3.text = RandomView.donutData2.toString()
        textView5.text = RandomView.donutData3.toString()

        // setting up two random days
        dayText1.text = RandomView.randomDay1
        dayText2.text= RandomView.randomDay2


        // Configurez et animez chaque DonutChart
        val colors = intArrayOf(Color.parseColor("#9FEC00"), Color.parseColor("#D9D9D9"))
        chartDonut1.donutColors = colors
        chartDonut1.animation.duration = 1000L
        chartDonut1.animate(listOf(RandomView.normalizedDonutData1, 100f - RandomView.normalizedDonutData1))


        chartDonut2.donutColors = colors
        chartDonut2.animation.duration = 1000L
        chartDonut2.animate(listOf(RandomView.normalizedDonutData2, 100f - RandomView.normalizedDonutData2))

        chartDonut3.donutColors = colors
        chartDonut3.animation.duration = 1000L
        chartDonut3.animate(listOf(RandomView.normalizedDonutData3, 100f - RandomView.normalizedDonutData3))

        // Show the normalized chart data.
        chart.animation.duration = 1000L
        chart.animate(RandomView.normalizedTrainingData)
        lineChart.animation.duration = 1000L
        lineChart.animate(RandomView.caloricData)

        return view
    }
}