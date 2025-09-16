package com.example.library.ui.auth

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RegisterFragment : Fragment(R.layout.fragment_register) {

    private val vm: RegisterViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val etUsername = view.findViewById<TextInputEditText>(R.id.etUsername)
        val etEmail = view.findViewById<TextInputEditText>(R.id.etEmail)
        val etPass = view.findViewById<TextInputEditText>(R.id.etPass)
        val btnRegister = view.findViewById<MaterialButton>(R.id.btnRegister)

        // Registro
        btnRegister.setOnClickListener {
            vm.register(
                etUsername.text?.toString().orEmpty(),
                etEmail.text?.toString().orEmpty(),
                etPass.text?.toString().orEmpty()
            )
        }

        // Ir a login
        view.findViewById<TextView>(R.id.tvGoLogin).setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        // Observa estado UI
        viewLifecycleOwner.lifecycleScope.launch {
            vm.ui.collectLatest { state ->
                btnRegister.isEnabled = !state.loading
                if (state.error != null) {
                    Snackbar.make(view, state.error!!, Snackbar.LENGTH_SHORT).show()
                    vm.clearError()
                }
                if (state.success) {
                    Snackbar.make(view, "Cuenta creada con éxito", Snackbar.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                }
            }
        }
    }
}
