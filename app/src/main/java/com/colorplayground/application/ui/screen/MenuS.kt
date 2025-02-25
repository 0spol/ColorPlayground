package com.colorplayground.application.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun MenuS(
    navigateToMainS: () -> Unit,
    navigateToLoginPreviewS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val activePalette by viewModel.activePalette.collectAsState()
    ColorPlaygroundTheme(customPalette = activePalette) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.weight(1f))
                Text(text = "MENU SCREEN", fontSize = 25.sp)
                Spacer(modifier = Modifier.weight(1f))
                Button(onClick = { navigateToMainS() }) {
                    Text(text = "Navegar a Main Screen")
                }
                Button(onClick = { navigateToLoginPreviewS() }) {
                    Text(text = "Navegar a Login Preview")
                }

                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}
