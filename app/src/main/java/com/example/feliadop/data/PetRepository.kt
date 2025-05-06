package com.example.feliadop.data

import kotlin.collections.map

class PetRepository {

    suspend fun fetchPetsRepository(): List<Pet> =
        PetClient
            .instance
            .fetchPetsService()
            .data
            .map { it.toDomainModel() }

    suspend fun findPetById(id: Int): RemoteResult2 =
        PetClient
            .instance
            .fetchPetByIdService(id)
}

private fun RemotePet.toDomainModel(): Pet =
    Pet(
        id = id,
        name = nombre,
        poster = imagen
    )