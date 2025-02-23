package com.colorplayground.application.ui.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun DynamicButton(backgroundColor: Color, text: String, onClick: () -> Unit, modifier: Modifier) {
    val textColor = if (isColorLight(backgroundColor)) Color.Black else Color.White

    Button(
        onClick = { /* ação do botão */ },
        colors = ButtonDefaults.buttonColors(backgroundColor)
    ) {
        Text(text, color = textColor)
    }
}

// Função para verificar se a cor é clara ou escura
fun isColorLight(color: Color): Boolean {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()

    val brightness = (red + green + blue) / 3 // Média dos canais RGB
    return brightness >= 128 // Se for 128 ou mais, a cor é clara
}