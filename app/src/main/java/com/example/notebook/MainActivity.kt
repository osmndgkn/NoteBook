    package com.example.notebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.room.Room



    val TAG : String = "Main activity log screen"

    class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setupActionBarWithNavController(findNavController(R.id.fragment))

    }
        override fun onSupportNavigateUp(): Boolean {
            val navController = findNavController(R.id.fragment)
            return navController.navigateUp() || super.onSupportNavigateUp()
        }
}