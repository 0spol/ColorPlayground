package com.colorplayground.application.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.R
import com.colorplayground.application.ui.component.Header
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@Composable
fun MenuS(
    navigateToMainS: () -> Unit,
    navigateToLoginPreviewS: () -> Unit,
) {
    val viewModel: ColorPaletteViewModel = hiltViewModel()
    val activePalette by viewModel.activePalette.collectAsState()

    ColorPlaygroundTheme(customPalette = activePalette) {
        Scaffold(
            topBar = { Header(navigateToMainS, stringResource(R.string.menu_SH)) },
            contentColor = MaterialTheme.colorScheme.onBackground
        ) { paddingValues ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                color = MaterialTheme.colorScheme.background
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    item {
                        Button(
                            onClick = { navigateToLoginPreviewS() },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(text = "Login Example")
                        }
                    }


                    // Botones "En Desarrollo"
                    val developmentButtons = listOf(
                        "Chat",
                        "Gallery",
                        "Posts",
                        "Profile",
                        "Twitter Clone"
                    )

                    items(developmentButtons) { label ->
                        Button(
                            onClick = { /* No hace nada */ },
                            modifier = Modifier
                                .fillMaxWidth(0.8f)
                                .height(56.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.secondary
                            )
                        ) {
                            Text(text = "👷🏻 $label - En Desarrollo")
                        }
                    }
                }
            }
        }
    }
}


