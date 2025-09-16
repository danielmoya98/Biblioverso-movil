package com.example.library.ui.profile


import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.library.R
import com.example.library.core.Prefs
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import androidx.navigation.fragment.findNavController

class ProfileFragment: Fragment(R.layout.fragment_profile) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etName = view.findViewById<TextInputEditText>(R.id.etName)
        val etEmail = view.findViewById<TextInputEditText>(R.id.etEmail)

        view.findViewById<MaterialButton>(R.id.btnSave).setOnClickListener {
            Snackbar.make(view, "Perfil actualizado", Snackbar.LENGTH_SHORT).show()
        }

        view.findViewById<MaterialButton>(R.id.btnLogout).setOnClickListener {
            Prefs.setLoggedIn(requireContext(), false)
            findNavController().navigate(R.id.welcomeFragment)
        }
    }
}