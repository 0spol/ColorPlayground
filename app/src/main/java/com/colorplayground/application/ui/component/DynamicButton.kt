package com.colorplayground.application.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DynamicButton(backgroundColor: Color, text: String, onClick: () -> Unit, modifier: Modifier) {

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = modifier
    ) {
        Text(text, color = MaterialTheme.colorScheme.tertiary)
    }
}
