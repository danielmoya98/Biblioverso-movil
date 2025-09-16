package com.example.library.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.example.library.core.Prefs

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.postDelayed({
            val firstRun = Prefs.isFirstRun(requireContext())
            val loggedIn = Prefs.isLoggedIn(requireContext())
            val action = when {
                firstRun -> SplashFragmentDirections.actionSplashToOnboarding()
                loggedIn -> SplashFragmentDirections.actionSplashToHome()
                else -> SplashFragmentDirections.actionSplashToWelcome()
            }
            findNavController().navigate(action)
        }, 1200)
    }
}
