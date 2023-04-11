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
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.navOptions
import com.core.designsystem.component.BottomBar
import com.core.designsystem.component.TopBar
import com.example.rickrolled.navigation.NavigationHost
import com.example.rickrolled.utils.ShowBars
import com.feature.character_list.CharacterListScreen
import com.feature.character_list.navigation.navigateToCharacterList
import com.feature.episode.navigation.navigateToEpisodeList
import com.feature.favorites.FavoritesViewModel
import com.feature.favorites.navigation.navigateToFavoritesScreen
import com.feature.location_list.navigation.navigateToLocationList
import com.feature.settings.navigation.navigateToSettingsScreen
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    favoritesViewModel: FavoritesViewModel = getViewModel(),
    mainScreenViewModel: MainScreenViewModel = getViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
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
        "detail/{id}" -> {
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

    Scaffold(
        topBar = {
            TopBar(modifier = Modifier.background(MaterialTheme.colors.primary),
                topBarState = topBarState,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: "Home",
                navigateUp = { navController.navigateUp() },
                toFavoriteListScreen = { navController.navigateToFavoritesScreen() },
                toSettingsScreen = { navController.navigateToSettingsScreen() },
                clearAllFavorites = { favoritesViewModel.clearFavorites() })
        },
        bottomBar = {
            BottomBar(modifier = Modifier.background(MaterialTheme.colors.primary),
                bottomBarState = bottomBarState,
                toCharactersScreen = {
                    navController.navigateToCharacterList()
                },
                toEpisodesScreen = {
                    navController.navigateToEpisodeList()
                },
                toLocationsScreen = {
                    navController.navigateToLocationList()
                }
            )
        },
        modifier = Modifier.fillMaxSize(),
        backgroundColor = MaterialTheme.colors.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            //NetworkConnectionBox(isNetworkAvailable = mainScreenViewModel.isNetworkAvailable.value)
            NavigationHost(navController = navController)
        }
    }
}