package com.example.workout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.networking.ApiService
import kotlinx.coroutines.Dispatchers

class WorkoutViewModel(private val apiService: ApiService) : ViewModel() {

    fun postWorkoutQuery(requestData: CurrentOpenIARequest) = liveData(Dispatchers.IO) {
        try {
            val response = apiService.postQuery(requestData)
            if (response.isSuccessful && response.body() != null) {
                emit(response.body())
            } else {
                emit(null) // Handle error scenario
            }
        } catch (e: Exception) {
            emit(null) // Handle error scenario
        }
    }

    private fun onError(){

    }
}
