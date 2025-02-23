package com.colorplayground.application.ui.component

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation

@Composable
fun DynamicTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: @Composable (() -> Unit)? = null,
    backgroundColor: Color,
    keyboardOptions: KeyboardOptions,
    modifier: Modifier = Modifier,
    shape: RoundedCornerShape,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    val labelColor = if (isColorLightTextField(backgroundColor)) Color.Black else Color.White

    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            if (label != null) {
                CompositionLocalProvider(LocalContentColor provides labelColor) {
                    label()
                }
            }
        },
        keyboardOptions = keyboardOptions,
        visualTransformation = visualTransformation,
        modifier = modifier,
        shape = shape,
        colors = TextFieldDefaults.colors(
            focusedTextColor = labelColor,
            unfocusedTextColor = labelColor,
            disabledTextColor = labelColor.copy(alpha = 0.6f),
            errorTextColor = Color.Red,
            focusedContainerColor = backgroundColor,
            unfocusedContainerColor = backgroundColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.6f),
            errorContainerColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Red,
            focusedLabelColor = labelColor,
            unfocusedLabelColor = labelColor,
            disabledLabelColor = labelColor.copy(alpha = 0.6f),
            errorLabelColor = Color.Red
        )
    )
}

// Função para verificar se a cor é clara ou escura
fun isColorLightTextField(color: Color): Boolean {
    val red = (color.red * 255).toInt()
    val green = (color.green * 255).toInt()
    val blue = (color.blue * 255).toInt()

    val brightness = (red * 299 + green * 587 + blue * 114) / 1000 // Fórmula de luminosidade
    return brightness >= 128 // Se for 128 ou mais, a cor é clara
}