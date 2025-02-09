package com.colorplayground.application.ui.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.data.repository.ColorPaletteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
class ColorPaletteViewModel(private val repository: ColorPaletteRepository) : ViewModel() {

    private val _colorPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val colorPalettes: StateFlow<List<ColorPalette>> = _colorPalettes

    private val _savedPalettes = MutableStateFlow<List<ColorPalette>>(emptyList())
    val savedPalettes: StateFlow<List<ColorPalette>> = _savedPalettes

    init {
        // Obtener las paletas guardadas cuando se inicia el ViewModel
        loadSavedPalettes()
    }

    fun generatePalettes(count: Int) {
        // Lógica para generar nuevas paletas (puedes personalizar esto)
        _colorPalettes.value = List(count) {
            ColorPalette(
                id = it,
                primaryColor = Color.Black,  // Cambia esto según la lógica para generar colores aleatorios
                secondaryColor = Color.Gray,
                tertiaryColor = Color.Blue,
                errorColor = Color.Red,
                nombre = "Paleta ${it + 1}"
            )
        }
    }

    fun savePalette(palette: ColorPalette) {
        // Aquí guardas la paleta en la base de datos (o en tu repositorio)
        viewModelScope.launch {
            repository.savePalette(palette)
            loadSavedPalettes() // Vuelve a cargar las paletas guardadas
        }
    }

    private fun loadSavedPalettes() {
        viewModelScope.launch {
            repository.getAllPalettes().collect { palettes ->
                _savedPalettes.value = palettes
            }
        }
    }
}




