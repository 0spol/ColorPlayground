package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.model.ColorPalette
import javax.inject.Inject


class GenerateColorPalettesUseCase @Inject constructor(
    private val generateRandomColorUseCase: GenerateRandomColorUseCase,
) {

    fun execute(count: Int, size: Int): List<ColorPalette> {
        return List(count) { index ->
            ColorPalette(
                id = (size + index + 1).toLong(),
                primaryColor = generateRandomColorUseCase.execute(),
                secondaryColor = generateRandomColorUseCase.execute(),
                tertiaryColor = generateRandomColorUseCase.execute(),
                errorColor = generateRandomColorUseCase.execute(),
                name = "Paleta ${size + index + 1}"
            )
        }
    }
}





