package com.example.feliadop.data

import kotlin.collections.map

class PetRepository(private val petService: PetService) {

    suspend fun fetchPetsRepository(): List<Pet> =
        petService
            .fetchPetsService()
            .data
            .map { it.toDomainModel() }

    suspend fun findPetById(id: Int): RemoteResult2 =
        petService
            .fetchPetByIdService(id)
}

private fun RemotePet.toDomainModel(): Pet =
    Pet(
        id = id,
        name = nombre,
        poster = imagen
    )