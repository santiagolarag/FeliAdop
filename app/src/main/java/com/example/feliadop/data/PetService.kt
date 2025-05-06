package com.example.feliadop.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PetService {

    @GET("animales")
    suspend fun fetchPetsService(): RemoteResult

    @GET("animal/{id}")
    suspend fun fetchPetByIdService(
        @Path("id") id: Int,
    ): RemoteResult2
}