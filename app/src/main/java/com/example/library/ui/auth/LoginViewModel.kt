package com.example.library.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.model.Usuario
import com.example.library.data.repo.AuthRepository
import com.example.library.data.repo.RemoteAuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class LoginUiState(
    val loading: Boolean = false,
    val error: String? = null
)

class LoginViewModel(
    private val repo: AuthRepository = RemoteAuthRepository()
) : ViewModel() {

    private val _ui = MutableStateFlow(LoginUiState())
    val ui: StateFlow<LoginUiState> = _ui

    private val _user = MutableStateFlow<Usuario?>(null)
    val user: StateFlow<Usuario?> = _user

    fun login(login: String, pass: String) {
        if (login.isBlank() || pass.isBlank()) {
            _ui.value = LoginUiState(error = "Completa tus credenciales")
            return
        }
        viewModelScope.launch {
            _ui.value = LoginUiState(loading = true)
            try {
                val u = repo.login(login, pass)
                if (u == null) {
                    _ui.value = LoginUiState(error = "Credenciales inválidas")
                } else {
                    _user.value = u
                    _ui.value = LoginUiState(loading = false, error = null)
                }
            } catch (e: Exception) {
                _ui.value = LoginUiState(error = "Error de conexión")
            }
        }
    }

    fun clearError() { _ui.value = _ui.value.copy(error = null) }
}
