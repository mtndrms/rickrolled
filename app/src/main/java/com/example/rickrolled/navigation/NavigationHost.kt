package com.example.rickrolled.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rickrolled.ui.screen.MainScreen
import com.example.rickrolled.ui.screen.splash.SplashScreen
import com.feature.character_details.navigation.characterDetailScreen
import com.feature.character_list.navigation.characterListScreen
import com.feature.episode.navigation.episodeListScreen
import com.feature.favorites.navigation.favoritesScreen
import com.feature.location_list.navigation.locationListScreen
import com.feature.settings.navigation.settingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

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

        characterListScreen()
        characterDetailScreen()
        episodeListScreen()
        locationListScreen()
        settingsScreen()
        favoritesScreen()
    }
}