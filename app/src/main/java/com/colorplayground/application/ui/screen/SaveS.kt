package com.colorplayground.application.ui.screen

import android.app.Activity
import android.view.View
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LayoutCoordinates
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.R
import com.colorplayground.application.ui.component.AddPaletteCard
import com.colorplayground.application.ui.component.Header
import com.colorplayground.application.ui.component.MyCard
import com.colorplayground.application.ui.component.SaveScreenBottomBar
import com.colorplayground.application.ui.di.HasShownTutorial
import com.colorplayground.application.ui.di.SetTutorialShown
import com.colorplayground.application.ui.di.ShowTapTargetView
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel


@Composable
fun SaveS(navigateToMainS: () -> Unit) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val savedPalettes by viewModel.savedPalettes.collectAsState()
    val activePalette by viewModel.activePalette.collectAsState()
    val context = LocalContext.current
    var targetView by remember { mutableStateOf<View?>(null) }

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
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        modifier = Modifier.weight(1f).fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            AddPaletteCard { viewModel.generateAndSavePalette(1) }
                        }

                        if (savedPalettes.isEmpty()) {
                            item(span = { GridItemSpan(2) }) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(400.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "No hay paletas guardadas",
                                        color = Color.Black,
                                        fontSize = 18.sp
                                    )
                                }
                            }
                        } else {
                            items(savedPalettes) { palette ->
                                MyCard(
                                    colorPalette = palette,
                                    updatePaletteUseCase = viewModel.updatePaletteUseCase,
                                    deletePaletteUseCase = viewModel.deletePaletteUseCase,
                                    onSelect = { viewModel.selectPalette(palette) },
                                    onUpdate = { viewModel.updateOnePalette(palette) },
                                    onDelete = { viewModel.deleteOnePalette(palette) },
                                    onViewReady = { targetView = it }
                                )
                            }
                        }
                    }
                }
            }
        }

        LaunchedEffect(targetView) {
            if (targetView != null && !HasShownTutorial(context)) {
                ShowTapTargetView(context as Activity, targetView!!) {
                    if (viewModel.savedPalettes.value.isNotEmpty()) {
                        val targetPalette = viewModel.savedPalettes.value.first()
                        viewModel.selectPalette(targetPalette)
                    } else {
                        viewModel.generateAndSavePalette(1)
                    }
                }

                SetTutorialShown(context)
            }
        }
    }
}
