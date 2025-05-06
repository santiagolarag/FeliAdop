package com.example.feliadop.ui.screens.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feliadop.data.PetRepository
import com.example.feliadop.data.RemoteResult2
import kotlinx.coroutines.launch

class DetailViewModel(private val id: Int): ViewModel() {

    private val petRepository = PetRepository()

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            state = UiState(loading = false, pet = petRepository.findPetById(id))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val pet: RemoteResult2? = null
    )
}