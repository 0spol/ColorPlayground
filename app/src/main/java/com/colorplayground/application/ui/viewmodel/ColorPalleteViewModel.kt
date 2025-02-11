package com.colorplayground.application.ui.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.repository.ColorPaletteRepository
import com.colorplayground.application.domain.usecase.DeleteAllPalettesUseCase
import com.colorplayground.application.domain.usecase.GenerateColorPalettesUseCase
import com.colorplayground.application.domain.usecase.GetAllPalettesUseCase
import com.colorplayground.application.domain.usecase.SavePaletteUseCase
import com.colorplayground.application.domain.usecase.UpdateAllPalettesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class ColorPaletteViewModel @Inject constructor(
    private val savePaletteUseCase: SavePaletteUseCase,
    private val generateColorPalettesUseCase: GenerateColorPalettesUseCase,
    private val deleteAllPalettesUseCase: DeleteAllPalettesUseCase,
    private val updateAllPalettesUseCase: UpdateAllPalettesUseCase,
    private val getAllPalettesUseCase: GetAllPalettesUseCase,
) : ViewModel() {

    private val _colorPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())

    private val _savedPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val savedPalettes: StateFlow<List<ColorPalette>> = _savedPalettes

    init {
        getAllSavedPalettes()
    }

    fun generateAndSavePalette(count: Int) {
        val newPalettes = generateColorPalettesUseCase.execute(count, _colorPalettes.value.size)

        newPalettes.firstOrNull()?.let { palette ->
            savePalette(palette)
            Log.d("ColorPaletteViewModel", "Paleta generada y guardada: $palette")
        }
    }

    private fun savePalette(palette: ColorPalette) {
        viewModelScope.launch {
            savePaletteUseCase.execute(palette)
            getAllSavedPalettes()
            Log.d("ColorPaletteViewModel", "Paleta guardada: $palette")
        }
    }

    fun deleteAllPalettes() {
        viewModelScope.launch {
            deleteAllPalettesUseCase.execute()
            _savedPalettes.value = emptyList()
            Log.d("ColorPaletteViewModel", "Todas las paletas han sido eliminadas")
        }
    }

    fun updateAllPalettes() {
        viewModelScope.launch {
            updateAllPalettesUseCase.execute()
            Log.d("ColorPaletteViewModel", "Todas las paletas han sido actualizadas")
            getAllSavedPalettes()
        }
    }

    private fun getAllSavedPalettes() {
        viewModelScope.launch {
            getAllPalettesUseCase.execute().collect { paletteEntities ->
                _savedPalettes.value = paletteEntities
                Log.d("ColorPaletteViewModel", "Paletas guardadas cargadas: $paletteEntities")
            }
        }
    }
}









