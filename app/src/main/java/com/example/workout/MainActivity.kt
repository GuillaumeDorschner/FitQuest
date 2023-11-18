package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import com.example.workout.databinding.ActivityMainBinding
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // binding.firstButton.setOnClickListener {
        //     binding.firstButton.text = "Clicked"
        // }
    }
}