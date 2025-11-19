package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.CreatePetDto
import com.example.dev_app_mobile.data.remote.model.PetResponseDTO
import com.example.dev_app_mobile.data.remote.model.UpdatePetDto
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface PetService {
    @GET("pets")
    suspend fun getUserPets(): Response<List<PetResponseDTO>>

    @GET("pets/{id}")
    suspend fun getPetById(@Path("id") petId: String): Response<PetResponseDTO>

    @POST("pets")
    suspend fun createPet(@Body createPetDto: CreatePetDto): Response<PetResponseDTO>

    @PATCH("pets/{id}")
    suspend fun updatePet(@Path("id") petId: String, @Body updatePetDto: UpdatePetDto): Response<PetResponseDTO>

    @DELETE("pets/{id}")
    suspend fun deletePet(@Path("id") petId: String): Response<Unit>
}