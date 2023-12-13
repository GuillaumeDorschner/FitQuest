package com.example.workout

import Activity
import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import com.example.workout.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workout.fragments.*
import com.example.workout.networking.ApiConfig
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.viewmodel.WorkoutViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val viewModel = ViewModelProvider(this, WorkoutViewModelFactory(ApiConfig.getApiService())).get(
            WorkoutViewModel::class.java)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(Home())

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.activity -> replaceFragment(Activity())
                R.id.new_workout -> replaceFragment(New_Workout())
                R.id.favorites -> replaceFragment(Favorites())
                R.id.account -> replaceFragment(Home())

                else ->{

                }
            }
            true
        }
    }

    fun replaceFragment(frag : Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout,frag)
        fragmentTransaction.commit()
    }

    fun gotoNewWorkOut(){
        binding.bottomNav.selectedItemId = R.id.new_workout
    }

}