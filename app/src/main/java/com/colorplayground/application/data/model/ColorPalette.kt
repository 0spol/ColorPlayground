package com.colorplayground.application.data.model

data class ColorPalette(
    val id: Int,
    val primaryColor: androidx.compose.ui.graphics.Color,
    val secondaryColor: androidx.compose.ui.graphics.Color,
    val tertiaryColor: androidx.compose.ui.graphics.Color,
    val errorColor: androidx.compose.ui.graphics.Color,
    val nombre: String
)
