package com.example.library.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.library.R
import com.example.library.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHost = supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment
        val navController = navHost.navController
        binding.bottomNav.setupWithNavController(navController)

        // Oculta BottomNav en pantallas no-tab
        val hideOn = setOf(
            R.id.splashFragment, R.id.onboardingFragment, R.id.welcomeFragment,
            R.id.loginFragment, R.id.registerFragment, R.id.bookDetailFragment
        )

        navController.addOnDestinationChangedListener { _, dest, _ ->
            binding.bottomNav.visibility = if (dest.id in hideOn) View.GONE else View.VISIBLE
        }
    }
}
