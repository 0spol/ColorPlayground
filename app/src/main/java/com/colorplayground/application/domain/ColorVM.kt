package com.colorplayground.application.domain

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlin.random.Random
import com.colorplayground.application.data.di.ColorPalette

class ColorVM : ViewModel() {

    fun generateColorPalletes(count : Int) : List<ColorPalette> {
        return List(count) { index ->
            ColorPalette (
                id = index,
                primaryColor = randomColor(),
                secondaryColor = randomColor(),
                tertiaryColor = randomColor(),
                errorColor = randomColor(),
                nombre = "Paleta ${index + 1}"
            )
        }
    }

    private fun randomColor() : Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 1f
        )
    }


}