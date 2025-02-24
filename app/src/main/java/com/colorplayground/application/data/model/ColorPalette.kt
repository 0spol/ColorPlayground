package com.colorplayground.application.data.model

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val id: Long,
    val primaryColor: Color,
    val secondaryColor: Color,
    val tertiaryColor: Color,
    val errorColor: Color,
    val name: String
) {
    companion object {
        fun default() = ColorPalette(
            id = -1L,
            primaryColor = Color(0xFF6200EE),   // Morado
            secondaryColor = Color(0xFFFFFFFF), // Turquesa
            tertiaryColor = Color(0xFFFFC107),  // Ámbar
            errorColor = Color(0xFFB00020),     // Rojo
            name = "Default Palette"
        )
    }
}
