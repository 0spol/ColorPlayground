package com.colorplayground.application.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Brush
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

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
                Icon(Icons.Default.Save, contentDescription = "To save")
            }
            IconButton(onClick = generatePalette) {
                Icon(Icons.Default.AutoAwesome, contentDescription = "Generate Palette")
            }
//            IconButton(onClick = navigateToMenuS) {
//                Icon(Icons.Default.Brush, contentDescription = "Customize")
//            }
        }
    }
}