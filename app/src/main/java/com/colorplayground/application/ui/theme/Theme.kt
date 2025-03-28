package com.colorplayground.application.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.colorplayground.application.data.model.ColorPalette

private val DarkColorScheme = darkColorScheme(
    primary = black_Color,
    secondary = white_Color,
    tertiary = new_tint_color,
)

private val LightColorScheme = lightColorScheme(
    primary = black_Color,
    secondary = white_Color,
    tertiary = new_tint_color,

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun ColorPlaygroundTheme(
    customPalette: ColorPalette? = null,
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        customPalette != null -> lightColorScheme(
            primary = customPalette.primaryColor,
            secondary = customPalette.secondaryColor,
            tertiary = customPalette.tertiaryColor,
            error = customPalette.errorColor,
            background = customPalette.primaryColor,
            surface = customPalette.secondaryColor
        )
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}