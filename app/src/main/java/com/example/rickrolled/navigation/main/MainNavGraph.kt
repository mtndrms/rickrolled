package com.example.rickrolled.navigation.main

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rickrolled.navigation.character.characterGraph
import com.example.rickrolled.ui.screen.episode_list.EpisodeListScreen
import com.example.rickrolled.ui.screen.location_list.LocationScreen
import com.example.rickrolled.ui.screen.settings.SettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainNavGraph(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = MainNavDirections.Characters.route,
        route = "main"
    ) {
        characterGraph(navController)

        composable(route = MainNavDirections.Episodes.route) {
            EpisodeListScreen(navController = navController, viewModel = getViewModel())
        }

        composable(route = MainNavDirections.Locations.route) {
            LocationScreen()
        }

        composable(route = MainNavDirections.Settings.route) {
            SettingsScreen()
        }
    }
}