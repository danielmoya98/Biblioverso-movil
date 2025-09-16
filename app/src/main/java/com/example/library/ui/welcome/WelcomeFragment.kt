package com.example.library.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.google.android.material.button.MaterialButton

class WelcomeFragment : Fragment(R.layout.fragment_welcome) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<MaterialButton>(R.id.btnLogin)
            .setOnClickListener { findNavController().navigate(WelcomeFragmentDirections.actionWelcomeToLogin()) }
        view.findViewById<MaterialButton>(R.id.btnRegister)
            .setOnClickListener { findNavController().navigate(WelcomeFragmentDirections.actionWelcomeToRegister()) }
    }
}
