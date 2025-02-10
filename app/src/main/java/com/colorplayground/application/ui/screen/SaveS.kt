package com.colorplayground.application.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.ui.component.MyCard
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun SaveS(
    navigateToMainS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val savedPalettes by viewModel.savedPalettes.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (savedPalettes.isEmpty()) {
                Text(text = "No hay paletas guardadas", color = Color.White)
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(savedPalettes) { palette ->
                        MyCard(colorPalette = palette)
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = { navigateToMainS() }) {
                Text(text = "Volver")
            }
        }
    }
}



