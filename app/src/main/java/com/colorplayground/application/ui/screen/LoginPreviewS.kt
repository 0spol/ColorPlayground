package com.colorplayground.application.ui.screen

import android.app.Application
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.colorplayground.application.R
import com.colorplayground.application.data.repository.BitmapRepository
import com.colorplayground.application.ui.component.DynamicButton
import com.colorplayground.application.ui.component.DynamicTextField
import com.colorplayground.application.ui.theme.ColorPlaygroundTheme
import com.colorplayground.application.ui.theme.white_Color
import com.colorplayground.application.ui.viewmodel.LoginValidationVM
import com.colorplayground.application.ui.viewmodel.ColorPaletteViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPreviewS(
    navigateBack: () -> Unit,
) {
    val loginViewModel: LoginValidationVM = hiltViewModel()
    val paletteViewModel: ColorPaletteViewModel = hiltViewModel()
    val activePalette by paletteViewModel.activePalette.collectAsState()

    ColorPlaygroundTheme(customPalette = activePalette) {
        val application = LocalContext.current.applicationContext as Application

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var showDialog by remember { mutableStateOf(false) }
        var dialogMessage by remember { mutableStateOf("") }

        val loginState by loginViewModel.loginState.observeAsState()

        val successfulMessage = stringResource(id = R.string.successful)
        val failedMessage = stringResource(id = R.string.failed)

        val primaryColor = MaterialTheme.colorScheme.secondary
        val bitmapRepository = remember { BitmapRepository(application) }
        val gradientImageBitmap: ImageBitmap = remember(primaryColor) {
            bitmapRepository.generateGradientImageBitmap(R.drawable.tinta0, primaryColor)
        }

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.textView_Login)) },
                    navigationIcon = {
                        IconButton(onClick = navigateBack) {
                            Icon(imageVector = Icons.Filled.ArrowDownward, contentDescription = "Back")
                        }
                    }
                )
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(paddingValues)
                ) {
                    // Background Image
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(570.dp)
                            .align(Alignment.TopCenter)
                            .padding(start = 10.dp)
                    ) {
                        Image(
                            bitmap = gradientImageBitmap,
                            contentDescription = "tinta_goteando",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(60.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        // Top Icon
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(130.dp)
                                .background(MaterialTheme.colorScheme.secondary, shape = CircleShape)
                                .border(4.dp, white_Color, CircleShape)
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                                contentDescription = "Logo",
                                modifier = Modifier.size(110.dp)
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        // Título
                        Text(
                            text = stringResource(id = R.string.textView_Login),
                            style = MaterialTheme.typography.headlineMedium.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            color = MaterialTheme.colorScheme.secondary,
                        )

                        Spacer(modifier = Modifier.height(80.dp))

                        // Email Field
                        DynamicTextField(
                            value = email,
                            onValueChange = { email = it },
                            label = { Text(stringResource(id = R.string.textView_Email)) },
                            backgroundColor = MaterialTheme.colorScheme.tertiary,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp)
                        )

                        Spacer(modifier = Modifier.height(35.dp))

                        // Contraseña Field
                        DynamicTextField(
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(stringResource(id = R.string.textView_Contraseña)) },
                            backgroundColor = MaterialTheme.colorScheme.tertiary,
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                            visualTransformation = PasswordVisualTransformation(),
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(20.dp)
                        )

                        Spacer(modifier = Modifier.height(50.dp))

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 18.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            // Botón de Login
                            DynamicButton(
                                modifier = Modifier
                                    .fillMaxWidth(1f)
                                    .padding(horizontal = 20.dp),
                                backgroundColor = MaterialTheme.colorScheme.secondary,
                                text = stringResource(id = R.string.button_right),
                                onClick = { loginViewModel.validateLogin(email, password) }
                            )
                        }

                        LaunchedEffect(loginState) {
                            when (loginState) {
                                LoginValidationVM.LoginState.SUCCESS -> {
                                    dialogMessage = successfulMessage
                                    showDialog = true
                                }
                                LoginValidationVM.LoginState.FAILURE -> {
                                    dialogMessage = failedMessage
                                    showDialog = true
                                }
                                else -> {
                                    showDialog = false
                                }
                            }
                        }

                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = {
                                    showDialog = false
                                    loginViewModel.resetLoginState()
                                },
                                title = { Text(text = stringResource(id = R.string.topLogin)) },
                                text = { Text(text = dialogMessage) },
                                confirmButton = {
                                    Button(onClick = {
                                        showDialog = false
                                        loginViewModel.resetLoginState()
                                    }) {
                                        Text(text = stringResource(id = R.string.bottom))
                                    }
                                }
                            )
                        }
                    }
                }
            }
        )
    }
}
