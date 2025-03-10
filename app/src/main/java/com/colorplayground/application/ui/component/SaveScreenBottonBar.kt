package com.colorplayground.application.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun SaveScreenBottomBar(viewModel: ColorPaletteViewModel) {
    val context = LocalContext.current

    BottomAppBar(
        modifier = Modifier.height(70.dp),
        containerColor = MaterialTheme.colorScheme.tertiary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { viewModel.deleteAllPalettes() }) {
                Icon(Icons.Default.Delete, contentDescription = "Eliminar paletas")
            }

            IconButton(onClick = { viewModel.updateAllPalettes() }) {
                Icon(Icons.Default.Refresh, contentDescription = "Actualizar paletas")
            }
        }
    }
}