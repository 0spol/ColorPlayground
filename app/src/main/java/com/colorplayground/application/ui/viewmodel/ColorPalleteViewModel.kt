package com.colorplayground.application.ui.viewmodel

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.repository.ColorPaletteRepository
import com.colorplayground.application.domain.usecase.SavePaletteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ColorPaletteViewModel @Inject constructor(
    private val savePaletteUseCase: SavePaletteUseCase,
    private val repository: ColorPaletteRepository
) : ViewModel() {

    private val _colorPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val colorPalettes: StateFlow<List<ColorPalette>> = _colorPalettes

    private val _savedPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val savedPalettes: StateFlow<List<ColorPalette>> = _savedPalettes

    init {
        loadSavedPalettes()
    }

    // Generando colores más vivos
    fun generatePalettes(count: Int) {
        _colorPalettes.value = List(count) {
            ColorPalette(
                id =  System.currentTimeMillis(),
                primaryColor = Color(
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    1f
                ),
                secondaryColor = Color(
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    1f
                ),
                tertiaryColor = Color(
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    1f
                ),
                errorColor = Color(
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    Random.nextFloat() * 0.8f + 0.2f,
                    1f
                ),
                name = "Paleta ${savedPalettes.value.size + 1}"
            )
        }
        Log.d("ColorPaletteViewModel", "Paletas generadas: ${_colorPalettes.value}")
    }

    fun savePalette(palette: ColorPalette) {
        viewModelScope.launch {
            savePaletteUseCase.execute(palette)
            loadSavedPalettes() // Recarga las paletas guardadas después de guardar
            Log.d("ColorPaletteViewModel", "Paleta guardada: $palette")
        }
    }

    private fun loadSavedPalettes() {
        viewModelScope.launch {
            repository.getAllPalettes().collect { paletteEntities ->
                Log.d("ColorPaletteViewModel", "Paletas guardadas: $paletteEntities")
                _savedPalettes.value = paletteEntities
            }
        }
    }
}






