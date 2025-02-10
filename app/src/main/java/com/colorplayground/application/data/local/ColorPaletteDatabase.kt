package com.colorplayground.application.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ColorPaletteEntity::class], version = 1, exportSchema = false)
abstract class ColorPaletteDatabase : RoomDatabase() {
    abstract fun colorPaletteDao(): ColorPaletteDao
}
