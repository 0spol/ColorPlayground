package com.colorplayground.application.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.colorplayground.application.ui.theme.ColorUtils.isColorLight

@Composable
fun DynamicButton(backgroundColor: Color, text: String, onClick: () -> Unit, modifier: Modifier) {
    val textColor = if (isColorLight(backgroundColor)) Color.Black else Color.White

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = modifier
    ) {
        Text(text, color = textColor)
    }
}
