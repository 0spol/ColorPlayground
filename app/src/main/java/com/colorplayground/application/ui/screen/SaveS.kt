package com.colorplayground.application.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.R
import com.colorplayground.application.ui.component.Header
import com.colorplayground.application.ui.component.MyCard
import com.colorplayground.application.ui.component.SaveScreenBottomBar
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun SaveS(
    navigateToMainS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val savedPalettes by viewModel.savedPalettes.collectAsState()
    val activePalette by viewModel.activePalette.collectAsState()

    ColorPlaygroundTheme(customPalette = activePalette) {
        Scaffold(
            topBar = { Header(navigateToMainS, stringResource(R.string.save_SH)) },
            bottomBar = { SaveScreenBottomBar(viewModel) }
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.background // Color de fondo del Surface
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    if (savedPalettes.isEmpty()) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "No hay paletas guardadas",
                                color = Color.Black,
                                fontSize = 18.sp
                            )
                        }
                    } else {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.spacedBy(16.dp),
                            horizontalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            items(savedPalettes) { palette ->
                                MyCard(colorPalette = palette)
                            }
                        }
                    }
                }
            }
        }
    }
}
