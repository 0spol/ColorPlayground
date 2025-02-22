package com.colorplayground.application.di

import com.colorplayground.application.data.repository.ColorPaletteRepository
import com.colorplayground.application.domain.usecase.GenerateColorPalettesUseCase
import com.colorplayground.application.domain.usecase.GetSavedPalettesUseCase
import com.colorplayground.application.domain.usecase.SavePaletteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideSavePaletteUseCase(repository: ColorPaletteRepository): SavePaletteUseCase {
        return SavePaletteUseCase(repository)
    }

    @Provides
    fun provideGetSavedPalettesUseCase(repository: ColorPaletteRepository): GetSavedPalettesUseCase {
        return GetSavedPalettesUseCase(repository)
    }
}

