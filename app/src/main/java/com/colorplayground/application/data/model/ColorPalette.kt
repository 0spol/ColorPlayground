package com.colorplayground.application.data.model

import androidx.compose.ui.graphics.Color
import com.colorplayground.application.ui.theme.Purple40
import com.colorplayground.application.ui.theme.ambar_color
import com.colorplayground.application.ui.theme.red_color
import com.colorplayground.application.ui.theme.white_Color

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
            primaryColor = Purple40,
            secondaryColor = white_Color,
            tertiaryColor = ambar_color,
            errorColor = red_color,
            name = "Default Palette"
        )
    }
}
