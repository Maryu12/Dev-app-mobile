package com.example.dev_app_mobile.data.repository

import com.example.dev_app_mobile.data.remote.api.AuthService
import com.example.dev_app_mobile.data.remote.model.LoginDTO
import com.example.dev_app_mobile.data.remote.model.RegisterDTO
import com.example.dev_app_mobile.data.remote.network.RetrofitInstance
import com.example.dev_app_mobile.domain.model.User
import com.example.dev_app_mobile.domain.repository.UserRepository
import com.example.dev_app_mobile.util.Result
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val authService: AuthService
) : UserRepository {

    override suspend fun login(email: String, password: String): Result<User> {
        return try {
            val response = authService.login(LoginDTO(email, password))
            if (response.isSuccessful && response.body() != null) {
                val authResponse = response.body()!!
                // Actualizar Retrofit con el nuevo token
                RetrofitInstance.updateAuthToken(authResponse.accessToken)
                // Guardar token localmente (SharedPreferences/DataStore)
                Result.Success(authResponse.user.toDomain())
            } else {
                Result.Error("Login failed: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    // Implementar otros métodos...
}