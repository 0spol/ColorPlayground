package com.colorplayground.application.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.ui.component.AnimatedArrow
import com.colorplayground.application.ui.component.AppHeader
import com.colorplayground.application.ui.component.BottomNavigationBar
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun MainS(
    navigateToSaveS: () -> Unit,
    navigateToMenuS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val activePalette by viewModel.activePalette.collectAsState()

    ColorPlaygroundTheme(customPalette = activePalette) {
        Scaffold(
            topBar = { AppHeader() },
            bottomBar = {
                BottomNavigationBar(
                    navigateToSaveS = navigateToSaveS,
                    navigateToMenuS = navigateToMenuS,
                    generatePalette = { viewModel.generateAndSavePalette(1) }
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(20.dp))

                AnimatedArrow()

                Spacer(modifier = Modifier.height(10.dp))

                Button(
                    onClick = { viewModel.generateAndSavePalette(1) },
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(80.dp),
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
                ) {
                    Text(text = "🎨 Generar Paleta", fontSize = 20.sp)
                }
            }
        }
    }
}
