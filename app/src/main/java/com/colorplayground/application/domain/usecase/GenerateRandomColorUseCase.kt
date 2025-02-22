package com.colorplayground.application.domain.usecase

import androidx.compose.ui.graphics.Color
import javax.inject.Inject
import kotlin.random.Random

class GenerateRandomColorUseCase @Inject constructor() {
    fun execute(): Color {
        return Color(
            red = Random.nextFloat() * 0.8f + 0.2f,
            green = Random.nextFloat() * 0.8f + 0.2f,
            blue = Random.nextFloat() * 0.8f + 0.2f,
            alpha = 1f
        )
    }
}