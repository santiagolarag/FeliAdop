package com.example.feliadop.ui.screens.home

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.example.feliadop.data.Pet
import com.example.feliadop.data.PetRepository
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    var state by mutableStateOf(UiState())
        private set

    private val petRepository = PetRepository()

    fun onUiReady() {
        viewModelScope.launch {
            state = UiState(isLoading = true)
            state = UiState(
                pets = petRepository.fetchPetsRepository(),
                isLoading = false)
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val pets: List<Pet> = emptyList()
    )
}