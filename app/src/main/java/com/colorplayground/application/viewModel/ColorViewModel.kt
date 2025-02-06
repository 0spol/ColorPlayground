package com.colorplayground.application.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.colorplayground.application.model.ColorPalette
import kotlin.random.Random

class ColorViewModel : ViewModel() {

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