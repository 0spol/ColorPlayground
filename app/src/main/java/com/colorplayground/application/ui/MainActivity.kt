package com.colorplayground.application.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.colorplayground.application.navigation.NavWrapper
import com.colorplayground.application.core.ui.theme.ColorPlaygroundTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorPlaygroundTheme {
                NavWrapper()
            }
        }
    }
}