package com.example.library.data.api

import com.example.library.data.dto.ReservaDto
import retrofit2.http.GET
import retrofit2.http.Path

interface ReservaApi {
    @GET("app/reservas/{idUsuario}")
    suspend fun getReservas(@Path("idUsuario") idUsuario: Int): ReservasResponse
}

data class ReservasResponse(val reservas: List<ReservaDto>)
