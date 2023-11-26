package com.example.workout.model

import com.google.gson.annotations.SerializedName

data class CurrentOpenIARequest(

	@field:SerializedName("messages")
	val messages: List<MessagesItem?>? = null,

	@field:SerializedName("model")
	val model: String? = null
)

data class MessagesItem(

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("content")
	val content: String? = null
)
