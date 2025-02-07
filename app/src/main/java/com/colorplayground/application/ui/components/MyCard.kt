package com.colorplayground.application.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colorplayground.application.data.di.ColorPalette

@Composable
fun MyCard(colorPalette: ColorPalette) {
    Column(
        modifier = Modifier.padding(5.dp)
    ){
        Card(modifier = Modifier.size(width = 150.dp, height = 150.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(colorPalette.primaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(colorPalette.secondaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(colorPalette.tertiaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(colorPalette.errorColor))
            }
        }
        Text(
            text = "Primera paleta",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp).align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            color = Color.White
        )
    }
}
