package com.ospol.colorplayground.testes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ospol.colorplayground.R
import com.ospol.colorplayground.ui.theme.ColorPlaygroundTheme

class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
        ColorPlaygroundTheme {
            Surface (color = MaterialTheme.colorScheme.background ){
              ConversationItem()
            }

        }
    }
}
}

@Composable
fun Greeting(name: String){
    Text(text = "Hello $name!")
}

@Composable
fun ConversationItem() {
    Row (modifier = Modifier.fillMaxWidth().padding(top = 16.dp)){
        Image(
            modifier = Modifier.size(80.dp).padding(8.dp).clip(CircleShape) ,
            painter = painterResource(id = R.drawable.lata_de_pintura),
            contentDescription = "User's profile picture"
        )
        Column (modifier = Modifier.align(Alignment.CenterVertically).weight(1f)) {

            Text(
                modifier = Modifier.padding(4.dp),
                text = "Palette number 1",
                fontWeight = FontWeight.Bold
            )
            Text(
                modifier = Modifier.padding(4.dp),
                text = "blue, turquoise and pink"
            )
        }
        Text(text = "19:01")
    }

}

@Preview (showBackground = true)
@Composable
fun ConversationItemPreview(){
    ColorPlaygroundTheme {
        ConversationItem()
    }
}

@Composable
fun GreetingGroup(){
    Column {
        Greeting(name = "Bruno")
        Greeting(name = "JOhn")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ColorPlaygroundTheme {
        GreetingGroup()
    }
}
