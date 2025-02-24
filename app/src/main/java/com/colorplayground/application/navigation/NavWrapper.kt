package com.colorplayground.application.navigation

import android.app.Application
import androidx.compose.runtime.Composable
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
        composable<LoginPreviewS> {
            LoginPreviewS(
                navigateBack = { navController.popBackStack() },
                loginViewModel = hiltViewModel(),
                onLoginClick = { email, password ->

                },
                onChangeColorClick = {},
                application = LocalContext.current.applicationContext as Application
            )
        }
        composable<ImagePreviewS> {
            ImagePreviewS(
                navigateBack = { navController.popBackStack() },
            )
        }

    }
}