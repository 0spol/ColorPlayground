package com.colorplayground.application.ui.screen

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
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
                Text(
                    text = "Palette Generator",
                    fontSize = 28.sp,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.height(20.dp))

                // Flecha animada apuntando al botón
                AnimatedArrow()

                Spacer(modifier = Modifier.height(10.dp))

                // Botón grande en el centro
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

@Composable
fun BottomNavigationBar(
    navigateToSaveS: () -> Unit,
    navigateToMenuS: () -> Unit,
    generatePalette: () -> Unit
) {
    BottomAppBar(
        modifier = Modifier.height(70.dp),
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            IconButton(onClick = navigateToSaveS) {
                Icon(Icons.Default.Save, contentDescription = "Guardar")
            }
            IconButton(onClick = generatePalette) {
                Icon(Icons.Default.AutoAwesome, contentDescription = "Generar paleta")
            }
            IconButton(onClick = navigateToMenuS) {
                Icon(Icons.Default.Brush, contentDescription = "Personalizar")
            }
        }
    }
}

@Composable
fun AnimatedArrow() {
    val alphaAnim = rememberInfiniteTransition().animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(700, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Text(
        text = "⬇️",
        fontSize = 40.sp,
        modifier = Modifier.alpha(alphaAnim.value)
    )
}