package com.example.feliadop.ui.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.feliadop.ui.screens.home.Screen
import com.example.feliadop.R

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
                    AsyncImage(
                        model = pet?.data?.imagenes[0]?.imagen,
                        contentDescription = pet?.data?.nombre,
                        contentScale = ContentScale.Crop,

                        modifier = Modifier
                            .fillMaxWidth()
                            .aspectRatio(16 / 9f)
                        //.clip(MaterialTheme.shapes.small)

                    )
                    Text(
                        text = pet?.data?.nombre ?: "",
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.headlineMedium

                    )
                }
            }
        }
    }
}