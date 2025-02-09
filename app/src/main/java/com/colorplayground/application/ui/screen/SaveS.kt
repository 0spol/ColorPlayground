package com.colorplayground.application.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.ui.component.MyCard
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun SaveS(
    navigateToMainS: () -> Unit,
    viewModel: ColorPaletteViewModel = hiltViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
        val generatedPalettes by viewModel.colorPalettes.collectAsState()
        val savedPalettes by viewModel.savedPalettes.collectAsState()

        Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {
            // Botón para volver a MainScreen
            Button(onClick = { navigateToMainS() }) {
                Text(text = "Navegar a Main Screen")
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Botón para generar nuevas paletas
            Button(onClick = { viewModel.generatePalettes(10) }) {
                Text("Generar Nuevas Paletas")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("🎨 Paletas Generadas", color = Color.White, fontSize = 20.sp)
            LazyColumn {
                items(generatedPalettes.chunked(2)) { rowPalettes ->
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(rowPalettes) { palette ->
                            MyCard(colorPalette = palette) {
                                viewModel.savePalette(palette)
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            Text("💾 Paletas Guardadas", color = Color.White, fontSize = 20.sp)
            LazyColumn {
                items(savedPalettes.chunked(2)) { rowPalettes ->
                    LazyRow(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        items(rowPalettes) { palette ->
                            MyCard(colorPalette = palette)
                        }
                    }
                }
            }
        }
    }
}

