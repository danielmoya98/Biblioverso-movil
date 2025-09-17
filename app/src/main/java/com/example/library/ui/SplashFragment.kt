package com.example.library.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.example.library.core.Prefs
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment(R.layout.fragment_splash) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            delay(2000) // Simula un splash de 2 segundos

            if (Prefs.isLoggedIn(requireContext())) {
                // Ir directo a MainActivity
                startActivity(Intent(requireContext(), MainActivity::class.java))
                requireActivity().finish()
            } else {
                // Seguir flujo normal (onboarding o welcome)
                findNavController().navigate(R.id.action_splash_to_welcome)
            }
        }
    }
}
