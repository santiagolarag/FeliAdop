package com.example.feliadop.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feliadop.data.Pet
import com.example.feliadop.data.PetClient
import com.example.feliadop.data.PetRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    private val petRepository = PetRepository(PetClient.instance)

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(
                pets = petRepository.fetchPetsRepository(),
                loading = false)
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pets: List<Pet> = emptyList()
    )
}