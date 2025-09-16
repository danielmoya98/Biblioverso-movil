// data/api/AuthApi.kt
package com.example.library.data.api

import com.example.library.data.dto.LoginRequest
import com.example.library.data.dto.LoginResponse
import com.example.library.data.dto.RegisterRequest
import com.example.library.data.dto.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("app/login")
    suspend fun login(@Body request: LoginRequest): LoginResponse

    @POST("app/register")
    suspend fun register(@Body request: RegisterRequest): RegisterResponse
}
