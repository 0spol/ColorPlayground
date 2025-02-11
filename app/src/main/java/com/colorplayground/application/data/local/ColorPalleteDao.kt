package com.colorplayground.application.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ColorPaletteDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPalette(palette: ColorPaletteEntity)

    @Query("SELECT * FROM color_palette")
    fun getAllPalettes(): Flow<List<ColorPaletteEntity>>

    @Delete
    suspend fun deletePalette(palette: ColorPaletteEntity)

    @Query("DELETE FROM color_palette")
    suspend fun deleteAllPalettes()


}

