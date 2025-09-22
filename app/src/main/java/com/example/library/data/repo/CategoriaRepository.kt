package com.example.library.data.repo

import com.example.library.data.api.CategoriaApi
import com.example.library.data.dto.CategoriaDto
import com.example.library.data.dto.LibroDto

class CategoriaRepository(private val api: CategoriaApi) {

    suspend fun getCategorias(): List<CategoriaDto> {
        return api.getCategorias()
    }

    suspend fun getLibrosPorCategoria(id: Int): List<LibroDto> {
        return api.getLibrosPorCategoria(id)
    }
}
