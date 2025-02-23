package com.colorplayground.application.ui.theme

import androidx.compose.ui.graphics.Color

object ColorUtils {
    fun isColorLight(color: Color): Boolean {
        val red = (color.red * 255).toInt()
        val green = (color.green * 255).toInt()
        val blue = (color.blue * 255).toInt()

        val brightness = (red * 299 + green * 587 + blue * 114) / 1000 // Fórmula de luminosidade
        return brightness >= 128 // Se for 128 ou mais, a cor é clara
    }
}