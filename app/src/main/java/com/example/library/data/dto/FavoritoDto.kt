// data/dto/FavoritoDto.kt
package com.example.library.data.dto

data class FavoritoDto(
    val id_favorito: Long,
    val fecha_agregado: String,
    val id_libro: Long,
    val titulo: String,
    val portada: String?,
    val sinopsis: String
)
