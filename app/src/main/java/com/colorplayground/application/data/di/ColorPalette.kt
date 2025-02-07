package com.colorplayground.application.data.di

import android.graphics.Color

data class ColorPalette(
    val id: Int,
    val primaryColor: androidx.compose.ui.graphics.Color,
    val secondaryColor: androidx.compose.ui.graphics.Color,
    val tertiaryColor: androidx.compose.ui.graphics.Color,
    val errorColor: androidx.compose.ui.graphics.Color,
    val nombre: String
)
