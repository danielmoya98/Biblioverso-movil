package com.example.library.data.repo

import com.example.library.core.ApiClient
import com.example.library.data.api.ReservaApi

class ReservaRepository {
    private val api = ApiClient.retrofit.create(ReservaApi::class.java)

    suspend fun getReservasUsuario(idUsuario: Int) = api.getReservas(idUsuario).reservas
}
