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
fun MainS(navigateToSaveS: () -> Unit, navigateToMenuS: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "MAIN SCREEN", fontSize = 25.sp)
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { navigateToSaveS() }) {
            Text(text = "Navegar a Save Screen")
        }
        Button(onClick = { navigateToMenuS() }) {
            Text(text = "Navegar a Menu Screen")
        }
        Spacer(modifier = Modifier.weight(1f))
    }
}