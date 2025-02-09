package com.colorplayground.application.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.colorplayground.application.domain.ColorVM
import com.colorplayground.application.ui.components.MyCard

@Composable
fun SaveS(navigateToMainS: () -> Unit, viewModel: ColorVM = viewModel()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.Black
    ) {
    val colorPalette = viewModel.generateColorPalletes(16)
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(colorPalette.chunked(2)) { rowPalettes ->
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
            ) {
                items(rowPalettes) { palette ->
                    MyCard(colorPalette = palette)
                }
            }
        }

    }

}
    Button(onClick = { navigateToMainS() }) {
        Text(text = "Navegar a Main Screen")
    }
}