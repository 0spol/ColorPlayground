package com.ospol.colorplayground.controllers.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationController() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Menu
    ){
        composable(NavigationRoutes.Menu) {
//            MenuScreen { route -> navController.navigate(route) }
        }
    }
}