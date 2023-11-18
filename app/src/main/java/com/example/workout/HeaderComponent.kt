package com.example.workout

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView

class HeaderComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    private val imageView: ImageView

    init {
        LayoutInflater.from(context).inflate(R.layout.header, this, true)
        imageView = findViewById(R.id.ivUserProfile)
        setUpView()
    }

    private fun setUpView() {
        // Initialisation des vues
        val textViewUserName: TextView = findViewById(R.id.tvUserName)
        textViewUserName.text = "Guillaume"
    }

    fun setUserImage(url: String) {
        Glide.with(context)
            .load(url)
            .into(imageView)
    }
}
