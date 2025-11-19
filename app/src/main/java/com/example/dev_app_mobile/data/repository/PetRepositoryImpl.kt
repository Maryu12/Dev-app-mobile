package com.example.dev_app_mobile.data.repository

import com.example.dev_app_mobile.data.remote.api.PetService
import com.example.dev_app_mobile.data.remote.model.CreatePetDto
import com.example.dev_app_mobile.data.remote.model.UpdatePetDto
import com.example.dev_app_mobile.domain.model.Pet
import com.example.dev_app_mobile.domain.repository.PetRepository
import com.example.dev_app_mobile.util.Result
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val petService: PetService
) : PetRepository {

    override suspend fun getPetsByOwner(ownerId: String): Result<List<Pet>> {
        return try {
            val response = petService.getUserPets()
            if (response.isSuccessful && response.body() != null) {
                val pets = response.body()!!.map { it.toDomain() }
                Result.Success(pets)
            } else {
                Result.Error("Failed to fetch pets: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    override suspend fun getPetById(petId: String): Result<Pet> {
        return try {
            val response = petService.getPetById(petId)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!.toDomain())
            } else {
                Result.Error("Failed to fetch pet: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    override suspend fun addPet(pet: Pet): Result<Pet> {
        return try {
            val createPetDto = CreatePetDto(
                name = pet.name,
                species = when (pet.type) {
                    com.example.dev_app_mobile.domain.model.PetType.DOG -> "DOG"
                    com.example.dev_app_mobile.domain.model.PetType.CAT -> "CAT"
                    com.example.dev_app_mobile.domain.model.PetType.BIRD -> "BIRD"
                    com.example.dev_app_mobile.domain.model.PetType.OTHER -> "OTHER"
                },
                breed = pet.breed,
                birthDate = "", // Convertir de age a birthDate
                weight = pet.weight,
                photo = pet.profileImage,
                notes = "" // Agregar notes al domain model si es necesario
            )

            val response = petService.createPet(createPetDto)
            if (response.isSuccessful && response.body() != null) {
                Result.Success(response.body()!!.toDomain())
            } else {
                Result.Error("Failed to create pet: ${response.message()}")
            }
        } catch (e: Exception) {
            Result.Error("Network error: ${e.message}")
        }
    }

    // Implementar updatePet y deletePet...
}