package com.example.library.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.example.library.core.Prefs
import com.example.library.ui.MainActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginFragment : Fragment(R.layout.fragment_login) {

    private val vm: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val email = view.findViewById<TextInputEditText>(R.id.etEmail)
        val pass  = view.findViewById<TextInputEditText>(R.id.etPass)
        val btn   = view.findViewById<MaterialButton>(R.id.btnLogin)

        // Login
        btn.setOnClickListener {
            vm.login(email.text?.toString().orEmpty(), pass.text?.toString().orEmpty())
        }
        // IME action "Done"
        pass.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                btn.performClick(); true
            } else false
        }

        // Ir a registro
        view.findViewById<TextView>(R.id.tvGoRegister).setOnClickListener {
            findNavController().navigate(R.id.action_login_to_register)
        }

        // Observa estado UI
        viewLifecycleOwner.lifecycleScope.launch {
            vm.ui.collectLatest { state ->
                btn.isEnabled = !state.loading
                if (state.error != null) {
                    Snackbar.make(view, state.error, Snackbar.LENGTH_SHORT).show()
                    vm.clearError()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            vm.user.collectLatest { user ->
                if (user != null) {
                    Prefs.setLoggedIn(requireContext(), true)
                    Prefs.saveUser(requireContext(), user)

                    // Antes navegabas con NavController → ahora abrimos MainActivity
                    startActivity(Intent(requireContext(), MainActivity::class.java))
                    requireActivity().finish()
                }
            }
        }
    }
}
