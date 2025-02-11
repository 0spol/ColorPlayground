package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.model.ColorPalette
import javax.inject.Inject


class GenerateColorPalettesUseCase @Inject constructor(
    private val generateRandomColorUseCase: GenerateRandomColorUseCase,
    private val getAllPalettesUseCase: GetAllPalettesUseCase,
) {

    fun execute(count: Int, currentSize: Int): List<ColorPalette> {
        return List(count) { index ->
            ColorPalette(
                id = System.currentTimeMillis() + index,
                primaryColor = generateRandomColorUseCase.execute(),
                secondaryColor = generateRandomColorUseCase.execute(),
                tertiaryColor = generateRandomColorUseCase.execute(),
                errorColor = generateRandomColorUseCase.execute(),
                name = "Paleta ${currentSize + index + 1}"
            )
        }
    }
}



