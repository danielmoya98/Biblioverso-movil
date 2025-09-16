package com.example.library.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.model.Usuario
import com.example.library.data.repo.AuthRepository
import com.example.library.data.repo.RemoteAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class RegisterUiState(
    val loading: Boolean = false,
    val error: String? = null,
    val success: Boolean = false
)

class RegisterViewModel(
    private val repo: AuthRepository = RemoteAuthRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(RegisterUiState())
    val ui: StateFlow<RegisterUiState> = _ui

    private val _user = MutableStateFlow<Usuario?>(null)
    val user: StateFlow<Usuario?> = _user

    fun register(usuario: String, email: String, pass: String) {
        if (usuario.isBlank() || email.isBlank() || pass.isBlank()) {
            _ui.value = RegisterUiState(error = "Completa todos los campos")
            return
        }

        viewModelScope.launch {
            _ui.value = RegisterUiState(loading = true)
            try {
                val u = repo.register(usuario, email, pass)
                if (u == null) {
                    _ui.value = RegisterUiState(error = "No se pudo crear la cuenta")
                } else {
                    _user.value = u
                    _ui.value = RegisterUiState(success = true)
                }
            } catch (e: Exception) {
                _ui.value = RegisterUiState(error = "Error de conexión")
            }
        }
    }

    fun clearError() { _ui.value = _ui.value.copy(error = null) }
}
