package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.repository.ColorPaletteRepository
import javax.inject.Inject

class DeleteONEPaletteUseCase @Inject constructor(
    private val repository: ColorPaletteRepository
) {
    suspend fun execute(palette: ColorPalette) {
        repository.deletePalette(palette)
    }
}