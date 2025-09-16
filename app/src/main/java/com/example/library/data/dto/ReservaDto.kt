package com.example.library.data.dto

data class ReservaDto(
    val id_reserva: Long,
    val fecha_reserva: String,
    val estado: String,
    val id_libro: Long,
    val titulo: String,
    val portada: String?,
    val autor: String
)

