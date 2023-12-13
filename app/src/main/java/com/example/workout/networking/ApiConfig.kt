package com.example.workout.networking

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiConfig {

    companion object {
        fun getApiService(): ApiService {

            val loggingInterceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .connectTimeout(40, TimeUnit.SECONDS) // Set the connection timeout
                .readTimeout(40, TimeUnit.SECONDS)     // Set the read timeout
                .addInterceptor { chain ->
                    val original = chain.request()
                    val requestBuilder = original.newBuilder()
                        .header("Authorization", "Bearer " + "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")
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
                .addInterceptor(loggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.openai.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            return retrofit.create(ApiService::class.java)
        }
    }
}