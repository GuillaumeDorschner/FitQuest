package com.example.workout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imageViewHome = findViewById<ImageView>(R.id.ButtonHome)
        val imageViewActivity = findViewById<ImageView>(R.id.ButtonActivity)
        val imageViewNewActivity = findViewById<ImageView>(R.id.ButtonNewActivity)
        val imageViewFavoris = findViewById<ImageView>(R.id.ButtonFavoris)
        val imageViewProfil = findViewById<ImageView>(R.id.ButtonProfil)
    }
}