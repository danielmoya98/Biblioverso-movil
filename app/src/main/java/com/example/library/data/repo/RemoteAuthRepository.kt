// data/repo/RemoteAuthRepository.kt
package com.example.library.data.repo

import com.example.library.core.ApiClient
import com.example.library.data.api.AuthApi
import com.example.library.data.dto.LoginRequest
import com.example.library.data.dto.RegisterRequest
import com.example.library.data.model.Usuario

class RemoteAuthRepository : AuthRepository {
    private val api = ApiClient.retrofit.create(AuthApi::class.java)

    override suspend fun login(login: String, password: String): Usuario? {
        val res = api.login(LoginRequest(email = login, password = password))
        return res.user
    }

    override suspend fun register(usuario: String, email: String, password: String): Usuario? {
        val res = api.register(RegisterRequest(usuario, email, password))
        return res.user
    }
}
