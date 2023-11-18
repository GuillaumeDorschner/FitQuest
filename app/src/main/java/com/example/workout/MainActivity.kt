package com.example.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val headerFragment = supportFragmentManager.findFragmentById(R.id.headerFragment) as? HeaderFragment
        val imageUrl = "https://thispersondoesnotexist.com/"
        headerFragment?.setUserImage(imageUrl)
    }
}
