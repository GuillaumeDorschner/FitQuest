package com.example.workout.networking

import com.example.workout.model.CurrentOpenIAResponse
import com.example.workout.model.CurrentOpenIARequest
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @POST("v1/chat/completions")
    fun postQuery(@Body requestData: CurrentOpenIARequest): Call<CurrentOpenIAResponse>
}