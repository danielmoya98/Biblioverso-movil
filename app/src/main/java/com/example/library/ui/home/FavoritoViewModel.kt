// ui/home/FavoritoViewModel.kt
package com.example.library.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.dto.FavoritoDto
import com.example.library.data.repo.FavoritoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FavoritoViewModel(
    private val repo: FavoritoRepository = FavoritoRepository()
) : ViewModel() {

    private val _favoritos = MutableStateFlow<List<FavoritoDto>>(emptyList())
    val favoritos: StateFlow<List<FavoritoDto>> = _favoritos

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading

    fun loadFavoritos(idUsuario: Int) {
        viewModelScope.launch {
            _loading.value = true
            try {
                _favoritos.value = repo.getFavoritosUsuario(idUsuario)
            } catch (e: Exception) {
                _favoritos.value = emptyList()
            } finally {
                _loading.value = false
            }
        }
    }
}
