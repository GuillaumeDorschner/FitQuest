package com.example.workout

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.workout.databinding.ActivityMainBinding
import com.example.workout.fragments.*
import com.example.workout.fragments.New_Workout

class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.activity -> replaceFragment(Home())
                R.id.new_workout -> replaceFragment(New_Workout())
                R.id.favorites -> replaceFragment(Home())
                R.id.account -> replaceFragment(Home())

                else ->{

                }
            }
            true
        }
    }

    private fun replaceFragment(frag : Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,frag)
        fragmentTransaction.commit()
    }

}