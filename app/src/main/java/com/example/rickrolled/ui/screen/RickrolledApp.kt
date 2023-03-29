package com.example.rickrolled.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rickrolled.navigation.NavigationHost
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.navigation.episode.EpisodeNavDirections
import com.example.rickrolled.ui.components.TopBar
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RickrolledApp(
    modifier: Modifier = Modifier,
    favoritesViewModel: FavoritesViewModel = getViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route

    Scaffold(
        topBar = {
            if (currentScreen != null) {
                TopBar(
                    modifier = Modifier.background(MaterialTheme.colors.primary),
                    navController = navController,
                    canNavigateBack = navController.previousBackStackEntry != null,
                    currentScreen = currentScreen,
                    toEpisodeList = { navController.navigate(EpisodeNavDirections.root.route) },
                    toFavoriteList = { navController.navigate(CharacterNavDirections.favorites.route) },
                    clearAllFavorites = { favoritesViewModel.clearFavorites() }
                )
            }
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavigationHost(navController)
        }
    }
}