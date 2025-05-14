package com.example.feliadop.ui.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feliadop.R
import com.example.feliadop.ui.screens.common.LoadingProgressIndicator
import com.example.feliadop.ui.screens.common.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(detailViewModel: DetailViewModel,
                 onBack: () -> Unit) {
    val state by detailViewModel.state.collectAsState()
    val title = state.pet?.data?.nombre ?: ""
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Screen {
        Scaffold(
            topBar = {
                DetailTopBar(title, scrollBehavior, onBack)
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .verticalScroll(rememberScrollState())
            ) {
                if (state.loading) {
                    LoadingProgressIndicator(modifier = Modifier.padding(padding))
                }

                state.pet.let { pet ->

                    val petty = pet?.data

                    AsyncImage(
                        model = petty?.imagenes?.firstOrNull()?.imagen,
                        contentDescription = petty?.nombre,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    PetInfoText(label = "Edad", value = petty?.edad)
                    PetInfoText(label = "Descripción Física", value = petty?.descFisica)
                    PetInfoText(label = "Descripción Personalidad", value = petty?.descPersonalidad)
                    PetInfoText(label = "Descripción Adicional", value = petty?.descAdicional)
                    PetInfoText(label = "Región", value = petty?.region)
                    PetInfoText(label = "Comuna", value = petty?.comuna)
                }
            }
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun DetailTopBar(
    title: String,
    scrollBehavior: TopAppBarScrollBehavior,
    onBack: () -> Unit
) {
    TopAppBar(
        title = { Text(text = title) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowBack,
                    contentDescription = stringResource(id = R.string.back)
                )
            }
        }
    )
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