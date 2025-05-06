package com.example.feliadop.data

import kotlinx.serialization.SerialName
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
    @SerialName("desc_fisica") val descFisica: String,
    @SerialName("desc_personalidad") val descPersonalidad: String,
    @SerialName("desc_adicional") val descAdicional: String,
    val region: String,
    val comuna: String,
    val imagenes: List<Imagenes>
)

@Serializable
data class Imagenes (
    val imagen: String
)