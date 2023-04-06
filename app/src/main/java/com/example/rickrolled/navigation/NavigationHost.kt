package com.example.rickrolled.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.rickrolled.ui.screen.MainScreen
import com.example.rickrolled.ui.screen.settings.SettingsScreenViewModel
import com.example.rickrolled.ui.screen.splash.SplashScreen
import com.example.rickrolled.ui.theme.RickrolledTheme
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = "splash"
    ) {
        composable(route = "splash") {
            SplashScreen(navController)
        }

        composable(route = "main") {
            MainScreen()
        }
    }
}