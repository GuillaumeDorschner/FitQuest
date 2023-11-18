package com.example.workout.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.model.CurrentOpenIAResponse
import com.example.workout.networking.ApiService
import kotlinx.coroutines.Dispatchers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutViewModel(private val apiService: ApiService) : ViewModel() {

    val workoutResponse = MutableLiveData<CurrentOpenIAResponse?>()
    val errorMessage = MutableLiveData<String?>()

    fun postWorkoutQuery(requestData: CurrentOpenIARequest) {
        val call: Call<CurrentOpenIAResponse> = apiService.postQuery(requestData)
        call.enqueue(object : Callback<CurrentOpenIAResponse> {
            override fun onResponse(
                call: Call<CurrentOpenIAResponse>,
                response: Response<CurrentOpenIAResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    workoutResponse.value = response.body()
                } else {
                    onError("Error: Response unsuccessful")
                }
            }

            override fun onFailure(call: Call<CurrentOpenIAResponse>, t: Throwable) {
                onError("Error: ${t.localizedMessage}")
            }
        })
    }

    private fun onError(message: String) {
        errorMessage.value = message
    }
}
