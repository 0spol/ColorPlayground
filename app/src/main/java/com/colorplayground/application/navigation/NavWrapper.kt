package com.colorplayground.application.core.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.colorplayground.application.core.ui.screens.LoginPreviewS
import com.colorplayground.application.core.ui.screens.ImagePreviewS
import com.colorplayground.application.core.ui.screens.MainS
import com.colorplayground.application.core.ui.screens.MenuS
import com.colorplayground.application.navigation.ImagePreviewS
import com.colorplayground.application.navigation.LoginPreviewS
import com.colorplayground.application.navigation.MainS
import com.colorplayground.application.navigation.MenuS
import com.colorplayground.application.navigation.SaveS
import com.colorplayground.application.ui.screens.SaveS

@Composable
fun NavWrapper() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = MainS) {
        composable<MainS> {
            MainS(
                navigateToSaveS = { navController.navigate(SaveS) },
                navigateToMenuS = { navController.navigate(MenuS) })
        }
        composable<SaveS> {
            SaveS(
                navigateToMainS = { navController.navigate(MainS) },
                viewModel = viewModel()
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
            )
        }
        composable<ImagePreviewS> {
            ImagePreviewS(
                navigateBack = { navController.popBackStack() },
            )
        }

    }
}