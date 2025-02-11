package com.colorplayground.application.domain.usecase

import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UpdateAllPalettesUseCase @Inject constructor(
    private val generateRandomColorUseCase: GenerateRandomColorUseCase,
    private val getSavedPalettesUseCase: GetSavedPalettesUseCase,
    private val savePaletteUseCase: SavePaletteUseCase
) {

    suspend fun execute() {
        val palettes = getSavedPalettesUseCase.execute().first()

        val updatedPalettes = palettes.map { palette ->
            val newPrimaryColor = generateRandomColorUseCase.execute()
            val newSecondaryColor = generateRandomColorUseCase.execute()
            val newTertiaryColor = generateRandomColorUseCase.execute()
            val newErrorColor = generateRandomColorUseCase.execute()

            palette.copy(
                primaryColor = newPrimaryColor,
                secondaryColor = newSecondaryColor,
                tertiaryColor = newTertiaryColor,
                errorColor = newErrorColor
            )
        }

        updatedPalettes.forEach { updatedPalette ->
            savePaletteUseCase.execute(updatedPalette)
        }
    }
}





