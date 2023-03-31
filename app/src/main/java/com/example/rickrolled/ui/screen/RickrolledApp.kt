package com.example.rickrolled.ui.screen

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rickrolled.navigation.NavigationHost
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.navigation.episode.EpisodeNavDirections
import com.example.rickrolled.ui.components.NetworkConnectionBox
import com.example.rickrolled.ui.components.TopBar
import com.example.rickrolled.ui.screen.character_details.CharacterDetailsViewModel
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel
import com.example.rickrolled.utils.ConnectivityObserver
import com.example.rickrolled.utils.NetworkConnectivityObserver
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.get
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RickrolledApp(
    favoritesViewModel: FavoritesViewModel = getViewModel(),
    navController: NavHostController = rememberAnimatedNavController(),
    networkObserver: NetworkConnectivityObserver = get()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val state = MutableTransitionState(false).apply { targetState = false }
    val networkStatus by networkObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.background(MaterialTheme.colors.primary),
                navController = navController,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: "Home",
                toEpisodeList = { navController.navigate(EpisodeNavDirections.root.route) },
                toFavoriteList = { navController.navigate(CharacterNavDirections.favorites.route) },
                clearAllFavorites = { favoritesViewModel.clearFavorites() }
            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            horizontalAlignment = CenterHorizontally
        ) {
            NetworkConnectionBox(state = state)
            if (!state.targetState) {
                NavigationHost(navController)
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
    state.targetState = networkStatus == ConnectivityObserver.Status.Unavailable
            || networkStatus == ConnectivityObserver.Status.Lost
}