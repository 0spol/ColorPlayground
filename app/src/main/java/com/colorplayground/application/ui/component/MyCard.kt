package com.colorplayground.application.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colorplayground.application.data.model.ColorPalette
import com.colorplayground.application.domain.usecase.DeleteONEPaletteUseCase
import com.colorplayground.application.domain.usecase.UpdateONEPaletteUseCase
import kotlinx.coroutines.launch

@Composable
fun MyCard(
    colorPalette: ColorPalette,
    updatePaletteUseCase: UpdateONEPaletteUseCase,
    deletePaletteUseCase: DeleteONEPaletteUseCase,
    onSave: (() -> Unit)? = null
) {
    var showActions by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .size(width = 150.dp, height = 150.dp)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = {
                            showActions = !showActions
                        }
                    )
                },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                Column {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(colorPalette.primaryColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(colorPalette.secondaryColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(colorPalette.tertiaryColor)
                    )
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .background(colorPalette.errorColor)
                    )
                }

                if (showActions) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        verticalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            coroutineScope.launch {
                                updatePaletteUseCase.execute(colorPalette)
                            }
                        }) {
                            Text("Update")
                        }
                        Button(onClick = {
                            coroutineScope.launch {
                                deletePaletteUseCase.execute(colorPalette)
                            }
                        }) {
                            Text("Delete")
                        }
                    }
                }
            }
        }

        Text(
            text = colorPalette.name,
            modifier = Modifier.padding(top = 5.dp),
            textAlign = TextAlign.Center,
            fontSize = 15.sp,
            color = Color.Black
        )
    }
}