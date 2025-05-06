package com.example.feliadop.ui.screens.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.feliadop.ui.theme.FeliAdopTheme

@Composable
fun Screen(context: @Composable () -> Unit) {
    FeliAdopTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
            content = context
        )
    }
}