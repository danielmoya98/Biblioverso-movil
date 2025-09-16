package com.example.library.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Usuario(
    @SerializedName("id_usuario") val idUsuario: Int,  // 👈 mapeado al JSON
    val usuario: String?,
    val nombre: String?,
    val apellido: String?,
    val email: String?,
    val telefono: String?,
    val direccion: String?,
    val genero: String?,
    @SerializedName("fecha_nac") val fechaNac: String?, // también mapeado
    val nacionalidad: String?,
    val biografia: String?,
    val foto: String?,
    @SerializedName("id_rol") val idRol: Int?,         // también mapeado
    @SerializedName("rol_nombre") val rolNombre: String? // también mapeado
) : Serializable
