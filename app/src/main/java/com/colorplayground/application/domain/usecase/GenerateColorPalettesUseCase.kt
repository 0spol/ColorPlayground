package com.colorplayground.application.domain.usecase

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.colorplayground.application.data.model.ColorPalette
import kotlin.random.Random
import com.colorplayground.application.data.repository.ColorPaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class GenerateColorPalettesUseCase @Inject constructor(
    private val colorPaletteRepository: ColorPaletteRepository
) : ViewModel() {
    fun execute(count: Int): List<ColorPalette> {
        return List(count) { index ->
            ColorPalette(
                id = index,
                primaryColor = randomColor(),
                secondaryColor = randomColor(),
                tertiaryColor = randomColor(),
                errorColor = randomColor(),
                nombre = "Paleta ${index + 1}"
            )
        }
    }

    private fun randomColor(): Color {
        return Color(
            red = Random.nextFloat(),
            green = Random.nextFloat(),
            blue = Random.nextFloat(),
            alpha = 1f
        )
    }
}
