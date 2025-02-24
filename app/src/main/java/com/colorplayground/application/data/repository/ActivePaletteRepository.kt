package com.colorplayground.application.data.repository

import android.content.Context
import androidx.core.content.edit
import com.colorplayground.application.data.model.ColorPalette
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ActivePaletteRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private const val PREFS_NAME = "active_palette_prefs"
        private const val KEY_ACTIVE_PALETTE = "active_palette"
    }

    private val gson = Gson()
    private val _activePalette = MutableStateFlow<ColorPalette?>(null)
    val activePalette: StateFlow<ColorPalette?> = _activePalette

    init {
        _activePalette.value = getStoredActivePalette()
    }

    fun saveActivePalette(palette: ColorPalette) {
        val json = gson.toJson(palette)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE).edit {
            putString(KEY_ACTIVE_PALETTE, json)
        }
        _activePalette.value = palette
    }

    private fun getStoredActivePalette(): ColorPalette? {
        val json = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .getString(KEY_ACTIVE_PALETTE, null)
        return if (json != null) gson.fromJson(json, ColorPalette::class.java) else null
    }
}
