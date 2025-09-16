package com.example.library.core

import android.content.Context
import com.example.library.data.model.Usuario

object Prefs {
    private const val P = "app_prefs"
    private const val FIRST = "first_run"
    private const val LOGGED = "logged_in"

    fun isFirstRun(ctx: Context) = ctx.getSharedPreferences(P, 0).getBoolean(FIRST, true)
    fun setFirstRun(ctx: Context, v: Boolean) =
        ctx.getSharedPreferences(P, 0).edit().putBoolean(FIRST, v).apply()

    fun setLoggedIn(ctx: Context, v: Boolean) =
        ctx.getSharedPreferences(P, 0).edit().putBoolean(LOGGED, v).apply()

    fun isLoggedIn(ctx: Context) =
        ctx.getSharedPreferences(P, 0).getBoolean(LOGGED, false)

    fun saveUser(ctx: Context, u: Usuario) =
        ctx.getSharedPreferences(P, 0).edit()
            .putInt("uid", u.idUsuario)
            .putString("name", listOfNotNull(u.nombre, u.apellido).joinToString(" "))
            .putString("email", u.email)
            .putString("avatar", u.foto)
            .apply()

    // ✅ Devuelve null si no hay user
    fun getUserId(ctx: Context): Int? {
        val id = ctx.getSharedPreferences(P, 0).getInt("uid", -1)
        return if (id == -1) null else id
    }

    fun getUserName(ctx: Context): String? =
        ctx.getSharedPreferences(P, 0).getString("name", null)

    fun clearUser(ctx: Context) =
        ctx.getSharedPreferences(P, 0).edit().clear().apply()
}
