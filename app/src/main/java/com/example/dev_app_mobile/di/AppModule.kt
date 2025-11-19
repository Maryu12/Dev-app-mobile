package com.example.dev_app_mobile.di

import com.example.dev_app_mobile.data.remote.api.AuthService
import com.example.dev_app_mobile.data.remote.api.PetService
import com.example.dev_app_mobile.data.remote.api.AppointmentService
import com.example.dev_app_mobile.data.remote.api.VeterinarianService
import com.example.dev_app_mobile.data.remote.api.SubscriptionService
import com.example.dev_app_mobile.data.repository.UserRepositoryImpl
import com.example.dev_app_mobile.data.repository.PetRepositoryImpl
import com.example.dev_app_mobile.data.repository.AppointmentRepositoryImpl
import com.example.dev_app_mobile.domain.repository.UserRepository
import com.example.dev_app_mobile.domain.repository.PetRepository
import com.example.dev_app_mobile.domain.repository.AppointmentRepository
import com.example.dev_app_mobile.data.remote.network.RetrofitInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthService(): AuthService {
        return RetrofitInstance.retrofit.create(AuthService::class.java)
    }

    @Provides
    @Singleton
    fun providePetService(): PetService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(PetService::class.java)
    }

    @Provides
    @Singleton
    fun provideAppointmentService(): AppointmentService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(AppointmentService::class.java)
    }

    @Provides
    @Singleton
    fun provideVeterinarianService(): VeterinarianService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(VeterinarianService::class.java)
    }

    @Provides
    @Singleton
    fun provideSubscriptionService(): SubscriptionService {
        return RetrofitInstance.getAuthenticatedRetrofit().create(SubscriptionService::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(authService: AuthService): UserRepository {
        return UserRepositoryImpl(authService)
    }

    @Provides
    @Singleton
    fun providePetRepository(petService: PetService): PetRepository {
        return PetRepositoryImpl(petService)
    }

    @Provides
    @Singleton
    fun provideAppointmentRepository(appointmentService: AppointmentService): AppointmentRepository {
        return AppointmentRepositoryImpl(appointmentService)
    }
}