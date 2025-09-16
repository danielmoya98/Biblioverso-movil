package com.example.library.ui.notifications

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.google.android.material.button.MaterialButton

class NotificationFragment : Fragment(R.layout.fragment_notification) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Botón de volver (puede ser una flecha en el appbar)
        view.findViewById<MaterialButton>(R.id.btnBackToHome)?.setOnClickListener {
            findNavController().navigate(R.id.action_notificationFragment_to_homeFragment)
        }
    }
}
