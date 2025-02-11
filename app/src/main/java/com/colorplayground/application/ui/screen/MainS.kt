package com.colorplayground.application.ui.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun MainS(
    navigateToSaveS: () -> Unit,
    navigateToMenuS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()

    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "MAIN SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))

        Button(onClick = {
            viewModel.generateAndSavePalette(1)  // Llamamos a la función del ViewModel
        }) {
            Text(text = "Generar y Guardar Paleta")
        }

        Button(onClick = { navigateToSaveS() }) {
            Text(text = "Navegar a Save Screen")
        }
        Button(onClick = { navigateToMenuS() }) {
            Text(text = "Navegar a Menu Screen")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
