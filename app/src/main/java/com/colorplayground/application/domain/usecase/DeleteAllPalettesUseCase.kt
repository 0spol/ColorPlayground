package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.repository.ColorPaletteRepository
import javax.inject.Inject

class DeleteAllPalettesUseCase @Inject constructor(
    private val repository: ColorPaletteRepository
) {
    suspend fun execute() {
        repository.deleteAllPalettes()
    }
}
