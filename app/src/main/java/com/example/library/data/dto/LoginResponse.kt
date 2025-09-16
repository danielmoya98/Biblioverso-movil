package com.example.library.data.dto

import com.example.library.data.model.Usuario

data class LoginResponse(
    val message: String,
    val user: Usuario?
)