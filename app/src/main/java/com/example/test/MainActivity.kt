package com.example.test

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.test.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Change the color of the action bar
        val actionBar = supportActionBar
        actionBar?.setBackgroundDrawable(ColorDrawable(ContextCompat.getColor(this, R.color.blue))
        )

        // Inflate the view using view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the BottomNavigationView from the view
        val navView: BottomNavigationView = binding.navView

        // Get the NavController from the NavHostFragment
        val navController =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)
                ?.findNavController()

        // Set up the action bar with the NavController
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_APOD, R.id.apodListFragment, R.id.apodSearchFragment
            )
        )
        if (navController != null) { setupActionBarWithNavController(navController, appBarConfiguration) }

        // Set up the BottomNavigationView with the NavController
        if (navController != null) { navView.setupWithNavController(navController) }
    }
}