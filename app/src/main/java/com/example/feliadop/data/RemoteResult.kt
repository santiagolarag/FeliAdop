package com.example.feliadop.data

import kotlinx.serialization.Serializable

@Serializable
data class RemoteResult(
    val data: List<RemotePet>
)

@Serializable
data class RemotePet(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val color: String,
    val edad: String,
    val estado: String,
    val genero: String,
    val imagen: String
)

@Serializable
data class RemoteResult2(
    val data: RemotePetDetail
)

@Serializable
data class RemotePetDetail(
    val id: Int,
    val nombre: String,
    val tipo: String,
    val edad: String,
    val estado: String,
    val genero: String,
    val imagenes: List<Imagenes>
)

@Serializable
data class Imagenes (
    val imagen: String
)