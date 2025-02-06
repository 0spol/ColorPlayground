package com.colorplayground.application.core.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun MenuS(navigateToMainS: () -> Unit, navigateToLoginPreviewS: () -> Unit, navigateToImagePreview: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "MENU SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToMainS() }) {
            Text(text = "Navegar a Main Screen")
        }
        Button(onClick = { navigateToLoginPreviewS() }) {
            Text(text = "Navegar a Login Preview")
        }
        Button(onClick = { navigateToImagePreview() }) {
            Text(text = "Navegar a Image Preview")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}
