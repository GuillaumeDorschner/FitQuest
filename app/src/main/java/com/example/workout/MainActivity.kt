package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerComponent = findViewById<HeaderComponent>(R.id.headerComponent)
        val imageUrl = "https://thispersondoesnotexist.com/"
        headerComponent.setUserImage(imageUrl)
    }
}
