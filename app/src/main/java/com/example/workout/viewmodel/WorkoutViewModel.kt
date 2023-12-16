package com.example.workout.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.MutableLiveData
import com.example.workout.model.CurrentOpenIARequest
import com.example.workout.model.CurrentOpenIAResponse
import com.example.workout.networking.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WorkoutViewModel(private val apiService: ApiService) : ViewModel() {

    val workoutResponse = MutableLiveData<CurrentOpenIAResponse?>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()
    val searchKeyword = MutableLiveData<String>()

    fun postWorkoutQuery(requestData: CurrentOpenIARequest) {
        isLoading.value = true
        val call: Call<CurrentOpenIAResponse> = apiService.postQuery(requestData)
        call.enqueue(object : Callback<CurrentOpenIAResponse> {
            override fun onResponse(
                call: Call<CurrentOpenIAResponse>,
                response: Response<CurrentOpenIAResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {

                    workoutResponse.value = response.body()
                    Log.d("WorkoutViewModel", "Response: ${response.body()}")
                    isLoading.value = false
                } else {
                    onError("Error: Response unsuccessful")
                    Log.e("WorkoutViewModel", "Unsuccessful response: ${response.errorBody()?.string()}")
                    isLoading.value = false
                }
            }

            override fun onFailure(call: Call<CurrentOpenIAResponse>, t: Throwable) {
                onError("Error: ${t.localizedMessage}")
                // Log the failure
                Log.e("WorkoutViewModel", "API Call Failed: ${t.localizedMessage}")
            }
        })
    }

    private fun onError(message: String) {
        errorMessage.value = message
        // Log the error message
        Log.e("WorkoutViewModel", "Error: $message")
    }
}
