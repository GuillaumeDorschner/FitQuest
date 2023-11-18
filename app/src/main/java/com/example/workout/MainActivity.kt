package com.example.workout

import FooterFragment
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Charger le FooterFragment dans le conteneur
        val fragmentFooter: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentFooter.replace(R.id.footerContainer, FooterFragment())
        fragmentFooter.commit()

    }
}