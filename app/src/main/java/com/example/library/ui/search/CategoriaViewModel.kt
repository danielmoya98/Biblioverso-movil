package com.example.library.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.library.data.dto.CategoriaDto
import com.example.library.data.dto.LibroDto
import com.example.library.data.repo.CategoriaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CategoriaViewModel(private val repo: CategoriaRepository) : ViewModel() {

    private val _categorias = MutableStateFlow<List<CategoriaDto>>(emptyList())
    val categorias: StateFlow<List<CategoriaDto>> = _categorias

    private val _libros = MutableStateFlow<List<LibroDto>>(emptyList())
    val libros: StateFlow<List<LibroDto>> = _libros

    fun loadCategorias() {
        viewModelScope.launch {
            try {
                _categorias.value = repo.getCategorias()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun loadLibrosPorCategoria(id: Int) {
        viewModelScope.launch {
            try {
                _libros.value = repo.getLibrosPorCategoria(id)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
