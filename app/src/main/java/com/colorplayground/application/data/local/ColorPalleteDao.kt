package com.colorplayground.application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorPaletteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPalette(palette: ColorPaletteEntity)

    @Query("SELECT * FROM color_palettes")
    fun getAllPalettes(): Flow<List<ColorPaletteEntity>>

    @Delete
    suspend fun deletePalette(palette: ColorPaletteEntity)
}

