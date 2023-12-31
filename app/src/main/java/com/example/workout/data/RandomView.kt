package com.example.workout.data

import kotlin.random.Random
import com.example.workout.R
import com.example.workout.model.CardPopular

object RandomView {
    val trainingData: List<Pair<String, Float>>
    val caloricData: List<Pair<String, Float>>
    val donutData1: Int
    val donutData2: Int
    val donutData3: Int
    val randomDay1: String
    val randomDay2: String
    val normalizedTrainingData: List<Pair<String, Float>>
    val normalizedDonutData1: Float
    val normalizedDonutData2: Float
    val normalizedDonutData3: Float
    val cardPopularList: List<CardPopular>
    val donutdatahome: Int
    val barchartdatahome : Int
    val caloriesdatahome: Int
    val normalizeddonutdatahome : Float


    init {

        cardPopularList = listOf(
            CardPopular("Cbum", R.drawable.cbum, "https://www.instagram.com/cbum/"),
            CardPopular("Tibo Inshape", R.drawable.tibo, "https://www.youtube.com/user/OutLawzFR"),
            CardPopular("Arnold", R.drawable.arnold, "https://fr.wikipedia.org/wiki/Arnold_Schwarzenegger"),
            CardPopular("Mike Mentzers", R.drawable.mike, "https://fr.wikipedia.org/wiki/Mike_Mentzer")
        )

        barchartdatahome = Random.nextInt(1, 8000)


        caloriesdatahome = Random.nextInt(1, 2000)

        donutdatahome = Random.nextInt(1, 120)
        normalizeddonutdatahome = (donutdatahome / 120f) * 100


        val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val daysOfWeekFull = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        // Randomly generate training data
        trainingData = daysOfWeek.map {
            Pair(it, 0.1f+Random.nextFloat() * 1.9f) // Generates a random float between 0.1 and 2
        }

        // Randomly generate caloric data
        caloricData = daysOfWeek.map {
            Pair(it, 2f + Random.nextFloat() * 5f) // Generates a random float between 2 and 6
        }

        val MAX_DONUT2 = 8000
        val MAX_DONUT1 = 56000

        // Generate donutData3 (lowest walk value in a day)
        donutData3 = Random.nextInt(1, MAX_DONUT2 + 1) // Range: 1 to 8000

        // Generate donutData2 (highest walk value in a day), ensuring it's greater than donutData3
        donutData2 = Random.nextInt(donutData3, MAX_DONUT2 + 1) // Range: donutData3 to 8000

        // Calculate a more realistic minimum for the total
        // Assuming the other days are distributed between the highest and lowest values
        val minTotal = donutData2 + 6*donutData3

        // Generate donutData1 (total walk value for the week)
        donutData1 = Random.nextInt(minTotal, MAX_DONUT1 + 1) // Range: max(minTotal, 2 * donutData2) to 56000



        // Select the first random day
         randomDay1 = daysOfWeekFull.random()

        // Create a new list excluding the first selected day
        val remainingDays = daysOfWeekFull - randomDay1

        // Select the second random day from the remaining days
        randomDay2 = remainingDays.random()

        val maxTrainingTime = 2f // 2 hours
        val maxSteps = 56000f
        val stepsDay = 8000f

        // Calculate normalized data
        normalizedTrainingData = trainingData.map { (day, hours) ->
            Pair(day, hours / maxTrainingTime)
        }




        normalizedDonutData1 = (donutData1 / maxSteps) * 100
        normalizedDonutData2 = (donutData2 / stepsDay) * 100
        normalizedDonutData3 = (donutData3 / stepsDay) * 100
    }
}