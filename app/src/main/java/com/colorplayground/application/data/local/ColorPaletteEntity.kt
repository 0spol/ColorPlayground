package com.colorplayground.application.data.local

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.colorplayground.application.data.model.ColorPalette

@Entity(tableName = "color_palette")
data class ColorPaletteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val primaryRed: Float,
    val primaryGreen: Float,
    val primaryBlue: Float,
    val primaryAlpha: Float,

    val secondaryRed: Float,
    val secondaryGreen: Float,
    val secondaryBlue: Float,
    val secondaryAlpha: Float,

    val tertiaryRed: Float,
    val tertiaryGreen: Float,
    val tertiaryBlue: Float,
    val tertiaryAlpha: Float,

    val errorRed: Float,
    val errorGreen: Float,
    val errorBlue: Float,
    val errorAlpha: Float,

    val nombre: String
) {
    companion object {
        fun fromDomain(palette: ColorPalette): ColorPaletteEntity {
            return ColorPaletteEntity(
                id = palette.id,
                primaryRed = palette.primaryColor.red,
                primaryGreen = palette.primaryColor.green,
                primaryBlue = palette.primaryColor.blue,
                primaryAlpha = palette.primaryColor.alpha,

                secondaryRed = palette.secondaryColor.red,
                secondaryGreen = palette.secondaryColor.green,
                secondaryBlue = palette.secondaryColor.blue,
                secondaryAlpha = palette.secondaryColor.alpha,

                tertiaryRed = palette.tertiaryColor.red,
                tertiaryGreen = palette.tertiaryColor.green,
                tertiaryBlue = palette.tertiaryColor.blue,
                tertiaryAlpha = palette.tertiaryColor.alpha,

                errorRed = palette.errorColor.red,
                errorGreen = palette.errorColor.green,
                errorBlue = palette.errorColor.blue,
                errorAlpha = palette.errorColor.alpha,

                nombre = palette.name
            )
        }
    }

    // Función para convertir de la entidad a un objeto de dominio (ColorPalette)
    fun toDomain(): ColorPalette {
        return ColorPalette(
            id = id,
            primaryColor = Color(primaryRed, primaryGreen, primaryBlue, primaryAlpha),
            secondaryColor = Color(secondaryRed, secondaryGreen, secondaryBlue, secondaryAlpha),
            tertiaryColor = Color(tertiaryRed, tertiaryGreen, tertiaryBlue, tertiaryAlpha),
            errorColor = Color(errorRed, errorGreen, errorBlue, errorAlpha),
            name = nombre
        )
    }
}




