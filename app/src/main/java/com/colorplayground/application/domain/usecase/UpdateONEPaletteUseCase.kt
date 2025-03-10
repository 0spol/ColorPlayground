package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.model.ColorPalette
import javax.inject.Inject

class UpdateONEPaletteUseCase @Inject constructor(
    private val generateRandomColorUseCase: GenerateRandomColorUseCase,
    private val savePaletteUseCase: SavePaletteUseCase
) {
    suspend fun execute(palette: ColorPalette) {
        val newPrimaryColor = generateRandomColorUseCase.execute()
        val newSecondaryColor = generateRandomColorUseCase.execute()
        val newTertiaryColor = generateRandomColorUseCase.execute()
        val newErrorColor = generateRandomColorUseCase.execute()

        val updatedPalette = palette.copy(
            primaryColor = newPrimaryColor,
            secondaryColor = newSecondaryColor,
            tertiaryColor = newTertiaryColor,
            errorColor = newErrorColor
        )

        savePaletteUseCase.execute(updatedPalette)
    }
}