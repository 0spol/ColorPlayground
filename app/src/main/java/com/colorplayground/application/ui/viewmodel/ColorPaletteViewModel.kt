package com.colorplayground.application.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.domain.usecase.DeleteAllPalettesUseCase
import com.colorplayground.application.domain.usecase.GenerateColorPalettesUseCase
import com.colorplayground.application.domain.usecase.GetAllPalettesUseCase
import com.colorplayground.application.domain.usecase.SavePaletteUseCase
import com.colorplayground.application.domain.usecase.UpdateAllPalettesUseCase
import com.colorplayground.application.data.repository.ActivePaletteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ColorPaletteViewModel @Inject constructor(
    private val savePaletteUseCase: SavePaletteUseCase,
    private val generateColorPalettesUseCase: GenerateColorPalettesUseCase,
    private val deleteAllPalettesUseCase: DeleteAllPalettesUseCase,
    private val updateAllPalettesUseCase: UpdateAllPalettesUseCase,
    private val getAllPalettesUseCase: GetAllPalettesUseCase,
    private val activePaletteRepository: ActivePaletteRepository
) : ViewModel() {

    private val _colorPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val colorPalettes: StateFlow<List<ColorPalette>> = _colorPalettes

    private val _savedPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val savedPalettes: StateFlow<List<ColorPalette>> = _savedPalettes

    private val _activePalette = MutableStateFlow(activePaletteRepository.activePalette.value)
    val activePalette: StateFlow<ColorPalette?> = _activePalette

    init {
        getAllSavedPalettes()
    }

    fun generateAndSavePalette(count: Int) {
        val newPalettes = generateColorPalettesUseCase.execute(count, _savedPalettes.value.size)
        _colorPalettes.value += newPalettes

        if (newPalettes.isNotEmpty()) {
            setActivePalette(newPalettes.first())
        }

        viewModelScope.launch {
            newPalettes.forEach { palette ->
                savePaletteUseCase.execute(palette)
            }
            getAllSavedPalettes()
            Log.d("ColorPaletteViewModel", "Paletas generadas y guardadas: $newPalettes")
        }
    }

    fun deleteAllPalettes() {
        viewModelScope.launch {
            deleteAllPalettesUseCase.execute()
            _savedPalettes.value = emptyList()
            _colorPalettes.value = emptyList()
            setActivePalette(ColorPalette.default())
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
                _colorPalettes.value = paletteEntities
                Log.d("ColorPaletteViewModel", "Paletas guardadas cargadas: $paletteEntities")
            }
        }
    }

    private fun setActivePalette(palette: ColorPalette) {
        _activePalette.value = palette
        activePaletteRepository.saveActivePalette(palette)
    }
}
