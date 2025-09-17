// data/api/FavoritoApi.kt
package com.example.library.data.api

import com.example.library.data.dto.FavoritoDto
import retrofit2.http.GET
import retrofit2.http.Path

data class FavoritoResponse(
    val favoritos: List<FavoritoDto>
)

interface FavoritoApi {
    @GET("app/favoritos/{idUsuario}")
    suspend fun getFavoritos(@Path("idUsuario") idUsuario: Int): FavoritoResponse
}
