package com.colorplayground.application.ui.di

import android.content.Context
import androidx.room.Room
import com.colorplayground.application.data.local.ColorPaletteDao
import com.colorplayground.application.data.local.ColorPaletteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ColorPaletteDatabase {
        return Room.databaseBuilder(
            context,
            ColorPaletteDatabase::class.java,
            "color_palette_db"
        ).build()
    }

    @Provides
    fun provideColorPaletteDao(database: ColorPaletteDatabase): ColorPaletteDao {
        return database.colorPaletteDao()
    }
}

