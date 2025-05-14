package com.example.feliadop.ui.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feliadop.data.PetClient
import com.example.feliadop.data.PetRepository
import com.example.feliadop.data.RemoteResult2
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int): ViewModel() {

    private val petRepository = PetRepository(PetClient.instance)

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.value = UiState(loading = true)
            _state.value = UiState(loading = false, pet = petRepository.findPetById(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pet: RemoteResult2? = null
    )
}