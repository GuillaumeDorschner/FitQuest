package com.example.workout

import Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.example.workout.databinding.ActivityMainBinding
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.workout.fragments.*
import com.example.workout.networking.ApiConfig
import com.example.workout.viewmodel.WorkoutViewModel
import com.example.workout.viewmodel.WorkoutViewModelFactory
import io.github.cdimascio.dotenv.dotenv
import User;
import UserManagement

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var userManagement: UserManagement

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userManagement = UserManagement(applicationContext)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        

        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isLiked", false).apply()

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

        val viewModel = ViewModelProvider(this, WorkoutViewModelFactory(ApiConfig.getApiService())).get(WorkoutViewModel::class.java)

        if (userManagement.CurrentUser()!=null) {
            replaceFragment(Home())
        } else {
            replaceFragment(Welcome())
        }

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> replaceFragment(Home())
                R.id.activity -> replaceFragment(Activity())
                R.id.new_workout -> replaceFragment(New_Workout())
                R.id.favorites -> replaceFragment(Favorites())
                R.id.account -> replaceFragment(Profile())
                else ->{

                }
            }
            true
        }
    }

    private fun isUserLoggedIn(): Boolean {
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)
        return isLoggedIn
    }


    fun replaceFragment(frag: Fragment) {
        Log.d("mejje", "replaceFragment: $frag")
        if (frag is Welcome || frag is Login || frag is SignUp) {
            binding.bottomNav.visibility = View.GONE
        } else {
            binding.bottomNav.visibility = View.VISIBLE
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, frag)
        fragmentTransaction.commit()
    }

    fun gotoNewWorkOut(){
        binding.bottomNav.selectedItemId = R.id.new_workout
    }

    fun gotoHome(){
        binding.bottomNav.selectedItemId = R.id.home
    }
}