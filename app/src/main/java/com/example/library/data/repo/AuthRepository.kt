package com.example.library.data.repo

import com.example.library.data.model.Usuario

interface AuthRepository {
    suspend fun login(login: String, password: String): Usuario?
    suspend fun register(usuario: String, email: String, password: String): Usuario?
}
