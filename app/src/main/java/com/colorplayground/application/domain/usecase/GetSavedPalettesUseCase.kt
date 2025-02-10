package com.colorplayground.application.domain.usecase

import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.repository.ColorPaletteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetSavedPalettesUseCase @Inject constructor(
    private val repository: ColorPaletteRepository
) {
    fun execute(): Flow<List<ColorPalette>> {
        return repository.getAllPalettes()
    }
}


