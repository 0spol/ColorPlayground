package com.colorplayground.application.data.local

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.colorplayground.application.data.model.ColorPalette

@Entity(tableName = "color_palettes")
data class ColorPaletteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val primaryColor: Long,
    val secondaryColor: Long,
    val tertiaryColor: Long,
    val errorColor: Long,
    val nombre: String
) {
    // Convierte de la entidad a tu modelo de dominio (ColorPalette)
    fun toDomain(): ColorPalette {
        return ColorPalette(
            id = id,
            primaryColor = Color(primaryColor),
            secondaryColor = Color(secondaryColor),
            tertiaryColor = Color(tertiaryColor),
            errorColor = Color(errorColor),
            nombre = nombre
        )
    }

    companion object {
        // Convierte de tu modelo de dominio (ColorPalette) a una entidad (ColorPaletteEntity)
        fun fromDomain(colorPalette: ColorPalette): ColorPaletteEntity {
            return ColorPaletteEntity(
                id = colorPalette.id,
                primaryColor = colorPalette.primaryColor.value.toLong(),
                secondaryColor = colorPalette.secondaryColor.value.toLong(),
                tertiaryColor = colorPalette.tertiaryColor.value.toLong(),
                errorColor = colorPalette.errorColor.value.toLong(),
                nombre = colorPalette.nombre
            )
        }
    }
}


