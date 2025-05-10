package com.example.feliadop.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.feliadop.data.Pet
import com.example.feliadop.data.PetClient
import com.example.feliadop.data.PetRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    private val petRepository = PetRepository(PetClient.instance)

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(
                pets = petRepository.fetchPetsRepository(),
                loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pets: List<Pet> = emptyList()
    )
}