package com.example.rickrolled.ui.screen

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import com.core.designsystem.component.BottomBar
import com.core.designsystem.component.NetworkConnectionBox
import com.core.designsystem.component.TopBar
import com.example.rickrolled.ui.screen.splash.SplashScreen
import com.example.rickrolled.utils.ShowBars
import com.feature.character_details.CharacterDetailsScreen
import com.feature.character_details.navigation.navigateToCharacterDetailScreen
import com.feature.character_list.CharacterListScreen
import com.feature.character_list.navigation.navigateToCharacterListScreen
import com.feature.episode.EpisodeListScreen
import com.feature.episode.navigation.navigateToEpisodeListScreen
import com.feature.favorites.FavoritesScreen
import com.feature.favorites.FavoritesViewModel
import com.feature.favorites.navigation.navigateToFavoritesScreen
import com.feature.location_list.LocationListScreen
import com.feature.location_list.navigation.navigateToLocationListScreen
import com.feature.settings.SettingsScreen
import com.feature.settings.navigation.navigateToSettingsScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import okio.IOException
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val favoritesViewModel: FavoritesViewModel = getViewModel()
    val mainScreenViewModel: MainScreenViewModel = getViewModel()
    val navController: NavHostController = rememberAnimatedNavController()

    val backStackEntry by navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    rememberSystemUiController().run {
        setStatusBarColor(MaterialTheme.colors.primary, darkIcons = false)
        setNavigationBarColor(MaterialTheme.colors.primary, darkIcons = false)
    }

    ShowBars(flag = true)

    when (backStackEntry?.destination?.route) {
        "splash" -> {
            bottomBarState.value = false
            topBarState.value = false
        }

        "character_detail/{id}" -> {
            bottomBarState.value = false
            topBarState.value = true
        }

        "favorites" -> {
            bottomBarState.value = false
            topBarState.value = true
        }

        "settings" -> {
            bottomBarState.value = false
            topBarState.value = true
        }

        else -> {
            bottomBarState.value = true
            topBarState.value = true
        }
    }

    val topLevelNavOptions = navOptions {
        launchSingleTop = true
        restoreState = true
    }

    Scaffold(
        topBar = {
            TopBar(
                modifier = Modifier.background(MaterialTheme.colors.primary),
                topBarState = topBarState,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: "Home",
                navigateUp = { navController.navigateUp() },
                toFavoriteListScreen = { navController.navigateToFavoritesScreen() },
                toSettingsScreen = { navController.navigateToSettingsScreen() },
                clearAllFavorites = favoritesViewModel::clearFavorites
            )
        },
        bottomBar = {
            BottomBar(modifier = Modifier.background(MaterialTheme.colors.primary),
                bottomBarState = bottomBarState,
                toCharactersScreen = {
                    navController.popBackStack()
                    navController.navigateToCharacterListScreen(topLevelNavOptions)
                },
                toEpisodesScreen = {
                    navController.popBackStack()
                    navController.navigateToEpisodeListScreen(topLevelNavOptions)
                },
                toLocationsScreen = {
                    navController.popBackStack()
                    navController.navigateToLocationListScreen(topLevelNavOptions)
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            NetworkConnectionBox(mainScreenViewModel.state, mainScreenViewModel.isNetworkAvailable)
            AnimatedNavHost(
                navController = navController,
                startDestination = "splash"
            ) {
                composable(route = "splash") {
                    SplashScreen(navController)
                }

                composable(route = "character_list") {
                    CharacterListScreen(
                        onCardClick = {
                            navController.navigateToCharacterDetailScreen(it)
                        },
                        isFavorite = favoritesViewModel::isFavorite,
                        addFavorite = favoritesViewModel::addFavorite,
                        removeFavorite = favoritesViewModel::removeFavorite
                    )
                }

                composable(
                    route = "character_detail/{id}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.IntType },
                    ),
                ) {
                    val id = remember { it.arguments?.getInt("id") }
                    CharacterDetailsScreen(id)
                }

                composable(route = "favorites") {
                    FavoritesScreen(
                        onCardClick = {
                            navController.navigateToCharacterDetailScreen(it)
                        }
                    )
                }

                composable(route = "settings") {
                    SettingsScreen()
                }

                composable(route = "episode_list") {
                    EpisodeListScreen()
                }

                composable(route = "location_list") {
                    LocationListScreen()
                }
            }
        }
    }
}