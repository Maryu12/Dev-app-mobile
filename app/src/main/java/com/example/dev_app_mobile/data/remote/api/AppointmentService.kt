package com.example.dev_app_mobile.data.remote.api

import com.example.dev_app_mobile.data.remote.model.AppointmentResponseDTO
import com.example.dev_app_mobile.data.remote.model.CreateAppointmentDTO
import com.example.dev_app_mobile.data.remote.model.UpdateAppointmentDTO
import com.example.dev_app_mobile.data.remote.model.VeterinarianAvailableSlotsDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface AppointmentService {
    @GET("appointments")
    suspend fun getUserAppointments(): Response<List<AppointmentResponseDTO>>

    @POST("appointments")
    suspend fun createAppointment(@Body createAppointmentDto: CreateAppointmentDTO): Response<AppointmentResponseDTO>

    @PATCH("appointments/{id}")
    suspend fun updateAppointment(
        @Path("id") appointmentId: String,
        @Body updateAppointmentDTO: UpdateAppointmentDTO
    ): Response<AppointmentResponseDTO>

    @DELETE("appointments/{id}")
    suspend fun deleteAppointment(@Path("id") appointmentId: String): Response<Unit>

    @GET("appointments/veterinarians/{id}")
    suspend fun getVeterinarianAvailability(@Path("id") vetId: String): Response<VeterinarianAvailableSlotsDTO>
}