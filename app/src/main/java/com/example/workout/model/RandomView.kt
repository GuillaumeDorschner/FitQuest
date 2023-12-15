package com.example.workout.model

import kotlin.random.Random

object RandomView {
    val trainingData: List<Pair<String, Float>>
    val caloricData: List<Pair<String, Float>>
    val donutData1: Int
    val donutData2: Int
    val donutData3: Int
    val randomDay1: String
    val randomDay2: String

    init {

        val daysOfWeek = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
        val daysOfWeekFull = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday")

        // Randomly generate training data
        trainingData = daysOfWeek.map {
            Pair(it, Random.nextFloat() * 2) // Generates a random float between 0 and 2
        }

        // Randomly generate caloric data
        caloricData = daysOfWeek.map {
            Pair(it, 1f + Random.nextFloat() * 6f) // Generates a random float between 1 and 7
        }

        // Randomly generate donut data with new specified ranges
        // Generate donutData3
        donutData3 = Random.nextInt(1, 8001) // Range: 1 to 8000

        // Generate donutData2, ensuring it's greater than donutData3
        donutData2 = Random.nextInt(donutData3, 8001) // Range: donutData3 to 8000

        // Calculate the sum of donutData2 and donutData3
        val sumOfDonut2And3 = donutData2 + donutData3

        // Generate donutData1, ensuring it's greater than the sum of donutData2 and donutData3
        // The maximum value for donutData1 needs to be large enough to allow this condition
        donutData1 = Random.nextInt(sumOfDonut2And3 + 1, 56001) // Range: sumOfDonut2And3 + 1 to 56000

        // Select the first random day
         randomDay1 = daysOfWeekFull.random()

        // Create a new list excluding the first selected day
        val remainingDays = daysOfWeekFull - randomDay1

        // Select the second random day from the remaining days
        randomDay2 = remainingDays.random()
    }
}