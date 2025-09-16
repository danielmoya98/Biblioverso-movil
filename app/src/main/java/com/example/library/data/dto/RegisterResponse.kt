package com.example.library.data.dto

import com.example.library.data.model.Usuario

data class RegisterResponse(
    val message: String,
    val user: Usuario?
)
