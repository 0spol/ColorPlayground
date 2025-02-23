package com.colorplayground.application.ui.screen

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import com.colorplayground.application.R
import com.colorplayground.application.data.repository.BitmapRepository
import com.colorplayground.application.ui.component.DynamicButton
import com.colorplayground.application.ui.component.DynamicTextField
import com.colorplayground.application.ui.theme.black_Color
import com.colorplayground.application.ui.theme.default_tint_color
import com.colorplayground.application.ui.theme.new_tint_color
import com.colorplayground.application.ui.theme.white_Color
import com.colorplayground.application.ui.viewmodel.LoginPaletteVM
import com.colorplayground.application.ui.viewmodel.LoginValidacionVM
import kotlin.random.Random


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPreviewS(
    onLoginClick: (String, String) -> Unit,
    onChangeColorClick: () -> Unit,
    navigateBack: () -> Unit,
    viewModel: LoginPaletteVM,
    loginViewModel: LoginValidacionVM,
    application: Application
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val bitmapRepository = remember { BitmapRepository(application) }
    val gradientImageBitmap: ImageBitmap = remember { bitmapRepository.generateGradientImageBitmap(R.drawable.tinta0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.textView_Login)) },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(black_Color)
                    .padding(paddingValues)
            ) {
                // Background Image
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(570.dp) // Defina a altura desejada
                        .align(Alignment.TopCenter)
                        .padding(start = 10.dp) // Alinhe a imagem ao topo
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
                            .background(new_tint_color, shape = CircleShape)
                            .border(4.dp, white_Color, CircleShape)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.ic_launcher_foreground),
                            contentDescription = "Logo",
                            modifier = Modifier.size(110.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Title
                    Text(
                        text = stringResource(id = R.string.textView_Login),
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        color = Color.White,
                    )

                    Spacer(modifier = Modifier.height(80.dp))

                    // Email Field
                    DynamicTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(id = R.string.textView_Email)) },
                        backgroundColor = default_tint_color,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Email),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp)
                    )

                    Spacer(modifier = Modifier.height(35.dp))

                    // Password Field
                    DynamicTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text(stringResource(id = R.string.textView_Contraseña)) },
                        backgroundColor = default_tint_color,
                        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
                        visualTransformation = PasswordVisualTransformation(),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp)
                    )

                    Spacer(modifier = Modifier.height(50.dp))

                    // Login Button
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // Botão de Login
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally)
                        ) {
                            // Botão de Login
                            DynamicButton(
                                modifier = Modifier.weight(1f),
                                backgroundColor = new_tint_color,
                                text = stringResource(id = R.string.button_right),
                                onClick = { onLoginClick(email, password) }
                            )

                            // Botão de Mudar Cor
                            DynamicButton(
                                backgroundColor = new_tint_color,
                                text = "Change Color",
                                onClick = {

                                },
                                modifier = Modifier.weight(1f)
                            )
                        }

                    }
                }
            }
        }
    )
}