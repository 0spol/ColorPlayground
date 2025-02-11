package com.colorplayground.application.domain.usecase

import javax.inject.Inject

class UpdateAllPalettesUseCase @Inject constructor(
    private val generateRandomColorUseCase: GenerateRandomColorUseCase,
    private val getSavedPalettesUseCase: GetSavedPalettesUseCase,
    private val savePaletteUseCase: SavePaletteUseCase
) {

    suspend fun execute() {
        getSavedPalettesUseCase.execute().collect { palettes ->
            val updatedPalettes = palettes.map { palette ->
                palette.copy(
                    primaryColor = generateRandomColorUseCase.execute(),
                    secondaryColor = generateRandomColorUseCase.execute(),
                    tertiaryColor = generateRandomColorUseCase.execute(),
                    errorColor = generateRandomColorUseCase.execute()
                )
            }

            updatedPalettes.forEach { updatedPalette ->
                savePaletteUseCase.execute(updatedPalette)
            }
        }
    }
}




