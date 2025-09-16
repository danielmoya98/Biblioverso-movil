// ui/home/ReservaViewModel.kt
package com.example.library.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.dto.ReservaDto
import com.example.library.data.repo.ReservaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReservaViewModel(
    private val repo: ReservaRepository = ReservaRepository()
) : ViewModel() {

    private val _reservas = MutableStateFlow<List<ReservaDto>>(emptyList())
    val reservas: StateFlow<List<ReservaDto>> = _reservas

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadReservas(idUsuario: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _reservas.value = repo.getReservasUsuario(idUsuario)
            } catch (e: Exception) {
                _reservas.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}
