package com.example.feliadop.ui.screens

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType
import com.example.feliadop.ui.screens.detail.DetailScreen
import com.example.feliadop.ui.screens.detail.DetailViewModel
import com.example.feliadop.ui.screens.home.HomeScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onPetClick = { pet ->
                    navController.navigate("detail/${pet.id}")
                }
            )
        }
        composable(
            route = "detail/{petId}",
            arguments = listOf(navArgument("petId") { type = NavType.IntType })
        ) { backStackEntry ->
            val petId = requireNotNull(backStackEntry.arguments?.getInt("petId"))
            DetailScreen(
                viewModel { DetailViewModel(id = petId) },
                onBack = { navController.popBackStack() }
            )
        }
    }
}
