package com.colorplayground.application.data.repository

import com.colorplayground.application.data.local.ColorPaletteDao
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.local.ColorPaletteEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ColorPaletteRepository @Inject constructor(private val dao: ColorPaletteDao) {

    fun getAllPalettes(): Flow<List<ColorPalette>> {
        return dao.getAllPalettes().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    suspend fun savePalette(palette: ColorPalette) {
        dao.insertPalette(ColorPaletteEntity.fromDomain(palette)) // Convertir de `ColorPalette` a `ColorPaletteEntity`
    }

    suspend fun deletePalette(palette: ColorPalette) {
        dao.deletePalette(ColorPaletteEntity.fromDomain(palette)) // Convertir de `ColorPalette` a `ColorPaletteEntity`
    }
}




