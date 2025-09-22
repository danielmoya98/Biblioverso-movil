package com.example.library.data.api

import com.example.library.data.dto.CategoriaDto
import com.example.library.data.dto.LibroDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CategoriaApi {

    @GET("app/categorias")
    suspend fun getCategorias(): List<CategoriaDto>

    @GET("app/categorias/{id}/libros")
    suspend fun getLibrosPorCategoria(@Path("id") id: Int): List<LibroDto>
}
