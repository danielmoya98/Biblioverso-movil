// data/repo/FavoritoRepository.kt
package com.example.library.data.repo

import com.example.library.core.ApiClient
import com.example.library.data.api.FavoritoApi

class FavoritoRepository {
    private val api = ApiClient.retrofit.create(FavoritoApi::class.java)

    suspend fun getFavoritosUsuario(idUsuario: Int) = api.getFavoritos(idUsuario).favoritos
}
