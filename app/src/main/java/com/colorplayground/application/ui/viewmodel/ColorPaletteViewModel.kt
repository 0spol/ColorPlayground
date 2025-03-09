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
import com.colorplayground.application.domain.usecase.DeleteONEPaletteUseCase
import com.colorplayground.application.domain.usecase.UpdateONEPaletteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class ColorPaletteViewModel @Inject constructor(
    private val savePaletteUseCase: SavePaletteUseCase,
    private val generateColorPalettesUseCase: GenerateColorPalettesUseCase,
    private val deleteAllPalettesUseCase: DeleteAllPalettesUseCase,
    private val updateAllPalettesUseCase: UpdateAllPalettesUseCase,
    private val getAllPalettesUseCase: GetAllPalettesUseCase,
    private val activePaletteRepository: ActivePaletteRepository,

    val deletePaletteUseCase: DeleteONEPaletteUseCase,
    val updatePaletteUseCase: UpdateONEPaletteUseCase,

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
        viewModelScope.launch {
            val existingIds = _savedPalettes.value.map { it.id }.toSet()
            val maxId = existingIds.maxOrNull() ?: 0L


            val newPalettes = generateColorPalettesUseCase.execute(count, _savedPalettes.value.size)

            val updatedPalettes = newPalettes.mapIndexed { index, palette ->
                val newId = maxId + index + 1
                palette.copy(
                    id = newId,
                    name = "Paleta $newId"
                )
            }

            _colorPalettes.value = _colorPalettes.value + updatedPalettes

            updatedPalettes.forEach { palette ->
                savePaletteUseCase.execute(palette)
            }
            getAllSavedPalettes()

            _savedPalettes.collect { updatedPalettes ->
                val lastPalette = _colorPalettes.value.lastOrNull()
                if (lastPalette != null) {
                    setActivePalette(lastPalette)
                }
            }

            Log.d("ColorPaletteViewModel", "Paletas geradas e salvas: $updatedPalettes")
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
                _savedPalettes.value = paletteEntities.sortedBy { it.id }
                _colorPalettes.value = paletteEntities
                Log.d("ColorPaletteViewModel", "Paletas guardadas cargadas: $paletteEntities")
            }
        }
    }

    fun deleteOnePalette(palette: ColorPalette) {
        viewModelScope.launch {
            deletePaletteUseCase.execute(palette)

            _savedPalettes.value = _savedPalettes.value.filter { it.id != palette.id }
            _colorPalettes.value = _colorPalettes.value.filter { it.id != palette.id }

            getAllSavedPalettes()
            Log.d("ColorPaletteViewModel", "Paleta borrada: $palette")
        }
    }

    fun updateOnePalette(palette: ColorPalette) {
        viewModelScope.launch {
            updatePaletteUseCase.execute(palette)
            getAllSavedPalettes()

            _savedPalettes.collect { updatedPalettes ->
                val updatedPalette = updatedPalettes.find { it.id == palette.id } ?: return@collect
                setActivePalette(updatedPalette)
                Log.d("ColorPaletteViewModel", "Paleta atualizada e ativada: $updatedPalette")
            }
        }
    }

    fun selectPalette(palette: ColorPalette) {
        _activePalette.value = palette
        activePaletteRepository.saveActivePalette(palette)
        Log.d("ColorPaletteViewModel", "Paleta selecionada: $palette")
    }




    private fun setActivePalette(palette: ColorPalette) {
        _activePalette.value = palette
        activePaletteRepository.saveActivePalette(palette)


        _activePalette.value = ColorPalette.default()
        _activePalette.value = palette
    }
}
