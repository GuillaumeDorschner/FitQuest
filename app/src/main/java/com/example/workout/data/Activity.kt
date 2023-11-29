package com.example.workout.data

import java.time.LocalDateTime

data class Activity(
    val date: LocalDateTime,
    val type: String,
    val time: Int, // minutes
    val bpm: Int,
    val kcal: Int,
    val steps: Int
)