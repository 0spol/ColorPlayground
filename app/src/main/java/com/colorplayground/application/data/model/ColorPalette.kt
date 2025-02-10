package com.colorplayground.application.data.model

import androidx.compose.ui.graphics.Color

data class ColorPalette(
    val id: Long,
    val primaryColor: Color,
    val secondaryColor: Color,
    val tertiaryColor: Color,
    val errorColor: Color,
    val name: String
)
