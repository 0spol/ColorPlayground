package com.colorplayground.application.core.ui.screens.testes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.colorplayground.application.core.ui.theme.ColorPlaygroundTheme

import kotlin.random.Random

class CardExample : ComponentActivity()     {


override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
        ColorPlaygroundTheme (darkTheme = isSystemInDarkTheme(), dynamicColor = false){
            Surface ( modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background){
                MyCardList()
            }

        }
    }
}
}

@Composable
fun MyCardList(){
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background).padding(10.dp).padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(7) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(25.dp, Alignment.CenterHorizontally)
            ) {
                items(2) {
                    MyCard2()
                }
            }
        }
    }
}

@Composable
fun MyCard2(){

    val primaryColor = randomColor()
    val secondaryColor = randomColor()
    val tertiaryColor = randomColor()
    val errorColor = randomColor()

    Column(
        modifier = Modifier.padding(5.dp)
    ) {
        Card(
            modifier = Modifier.size(width = 150.dp, height = 150.dp),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Column {
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(primaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(secondaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(tertiaryColor))
                Box(modifier = Modifier.weight(1f).fillMaxWidth().background(errorColor))
            }}
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

fun randomColor(): Color {
    return Color(
        red = Random.nextFloat(),
        green = Random.nextFloat(),
        blue = Random.nextFloat(),
        alpha = 1f
    )
}

@Preview(showBackground = true)
@Composable
fun MyCardPreview(){
    ColorPlaygroundTheme {
        MyCardList()
    }
}
