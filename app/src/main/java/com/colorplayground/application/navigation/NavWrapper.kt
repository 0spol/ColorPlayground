package com.colorplayground.application.navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.colorplayground.application.ui.screen.LoginPreviewS
import com.colorplayground.application.ui.screen.ImagePreviewS
import com.colorplayground.application.ui.screen.MainS
import com.colorplayground.application.ui.screen.MenuS
import com.colorplayground.application.ui.screen.SaveS
import com.colorplayground.application.ui.viewmodel.LoginPaletteVM
import com.colorplayground.application.ui.viewmodel.LoginValidacionVM

@Composable
fun NavWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainS) {
        composable<MainS> {
            MainS(
                navigateToSaveS = { navController.navigate(SaveS) },
                navigateToMenuS = { navController.navigate(MenuS) },
            )
        }
        composable<SaveS> {
            SaveS(
                navigateToMainS = { navController.navigate(MainS) },
            )

        }
        composable<MenuS> {
            MenuS(
                navigateToMainS = { navController.navigate(MainS) },
                navigateToLoginPreviewS = { navController.navigate(LoginPreviewS) },
                navigateToImagePreview = { navController.navigate(ImagePreviewS) },
            )
        }
        composable<MenuS> {
            val loginPaletteVM: LoginPaletteVM = hiltViewModel()
            val loginValidacionVM: LoginValidacionVM = hiltViewModel()
            val context = LocalContext.current.applicationContext as Application
            LoginPreviewS(
                navigateBack = { navController.popBackStack() },
                viewModel = loginPaletteVM,
                loginViewModel = loginValidacionVM,
                onLoginClick = { email, password ->
                    loginValidacionVM.validateLogin(
                        email,
                        password
                    )
                },
                onChangeColorClick = {
                    loginPaletteVM.chanceBackgroundColorAll(Color.Cyan)
                    loginPaletteVM.changeTintColor(Color.Cyan)
                    loginPaletteVM.changeButtonColor(Color.Cyan)
                    loginPaletteVM.changeButtonTextColor(Color.DarkGray)
                    loginPaletteVM.changeIconColor(Color.Cyan)
                    loginPaletteVM.changeTextColor(Color.White)
                    loginPaletteVM.changeBackgroundColor(Color.DarkGray)
                    loginPaletteVM.changeStrokeColor(Color.Cyan)
                },
                application = context
            )
        }
        composable<ImagePreviewS> {
            ImagePreviewS(
                navigateBack = { navController.popBackStack() },
            )
        }

    }
}