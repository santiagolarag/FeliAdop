package com.example.feliadop.ui.screens.navigation

sealed class NavRouter(val route: String) {
    data object Home : NavRouter("home")
    data object Detail : NavRouter("detail/{${NavArgs.PetId.key}}") {
        fun createRoute(petId: Int) = "detail/$petId"
    }
}


enum class NavArgs(val key: String) {
    PetId("petId")
}