package com.example.feliadop.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feliadop.R
import com.example.feliadop.ui.screens.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel,
                 onBack: () -> Unit) {
    val state = detailViewModel.state
    Screen {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = state.pet?.data?.nombre ?: "") },
                    navigationIcon = {
                        IconButton(
                            onClick =
                                onBack
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Default.ArrowBack,
                                contentDescription = stringResource(id = R.string.back)
                            )
                        }
                    }
                )
            }
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                if (state.loading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(padding),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }
                }

                state.pet.let { pet ->

                    val pety = pet?.data

                    AsyncImage(
                        model = pety?.imagenes?.firstOrNull()?.imagen,
                        contentDescription = pety?.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(MaterialTheme.shapes.small)

                    )
                    PetInfoText(label = "Edad", value = pety?.edad)
                    PetInfoText(label = "Descripción Física", value = pety?.descFisica)
                    PetInfoText(label = "Descripción Personalidad", value = pety?.descPersonalidad)
                    PetInfoText(label = "Descripción Adicional", value = pety?.descAdicional)
                    PetInfoText(label = "Región", value = pety?.region)
                    PetInfoText(label = "Comuna", value = pety?.comuna)
                }
            }
        }
    }
}

@Composable
fun PetInfoText(label: String, value: String?) {
    if (value != null && value.isNotBlank()) {
        Text(
            text = "$label: $value",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
            style = MaterialTheme.typography.bodyMedium
        )
    }
}