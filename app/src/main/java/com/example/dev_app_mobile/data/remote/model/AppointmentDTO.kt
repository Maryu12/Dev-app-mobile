package com.example.dev_app_mobile.data.remote.model

import com.google.gson.annotations.SerializedName

data class CreateAppointmentDTO(
    @SerializedName("petId") val petId: String,
    @SerializedName("scheduledAt") val scheduledAt: String,
    @SerializedName("serviceType") val serviceType: String,
    @SerializedName("vetId") val vetId: String? = null,
    @SerializedName("notes") val notes: String? = null
)

data class UpdateAppointmentDTO(
    @SerializedName("scheduledAt") val scheduledAt: String? = null,
    @SerializedName("serviceType") val serviceType: String? = null,
    @SerializedName("vetId") val vetId: String? = null,
    @SerializedName("notes") val notes: String? = null
)

data class AppointmentResponseDTO(
    @SerializedName("id") val id: String,
    @SerializedName("pet") val pet: PetResponseDTO,
    @SerializedName("scheduledAt") val scheduledAt: String,
    @SerializedName("serviceType") val serviceType: String,
    @SerializedName("veterinarian") val veterinarian: UserResponseDTO?,
    @SerializedName("duration") val duration: Int,
    @SerializedName("notes") val notes: String?,
    @SerializedName("createdAt") val createdAt: String
) {
    fun toDomain(): com.example.dev_app_mobile.domain.model.Appointment {
        return com.example.dev_app_mobile.domain.model.Appointment(
            id = id,
            petId = pet.id,
            veterinarianId = veterinarian?.id ?: "",
            serviceType = when (serviceType) {
                "MEDICAL_REVIEW" -> com.example.dev_app_mobile.domain.model.ServiceType.VETERINARY
                "BATH" -> com.example.dev_app_mobile.domain.model.ServiceType.GROOMING
                "HAIRCUT" -> com.example.dev_app_mobile.domain.model.ServiceType.GROOMING
                "DAYCARE" -> com.example.dev_app_mobile.domain.model.ServiceType.DAYCARE
                "PET_TRANSPORTATION" -> com.example.dev_app_mobile.domain.model.ServiceType.TRANSPORT
                else -> com.example.dev_app_mobile.domain.model.ServiceType.VETERINARY
            },
            date = scheduledAt.split("T")[0],
            time = scheduledAt.split("T")[1].substring(0, 5),
            status = com.example.dev_app_mobile.domain.model.AppointmentStatus.PENDING, // Por defecto
            notes = notes
        )
    }
}