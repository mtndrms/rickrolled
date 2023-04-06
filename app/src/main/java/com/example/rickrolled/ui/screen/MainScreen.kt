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
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.navigation.main.MainNavDirections
import com.example.rickrolled.navigation.main.MainNavGraph
import com.example.rickrolled.ui.components.BottomBar
import com.example.rickrolled.ui.components.NetworkConnectionBox
import com.example.rickrolled.ui.components.TopBar
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel
import com.example.rickrolled.utils.showBars
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import org.koin.androidx.compose.getViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen(
    favoritesViewModel: FavoritesViewModel = getViewModel(),
    navController: NavHostController = rememberAnimatedNavController()
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val topBarState = rememberSaveable { (mutableStateOf(true)) }

    rememberSystemUiController().run {
        setStatusBarColor(MaterialTheme.colors.primary, darkIcons = false)
        setNavigationBarColor(MaterialTheme.colors.primary, darkIcons = false)
    }

    showBars(flag = true)

    when (backStackEntry?.destination?.route) {
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
                navController = navController,
                canNavigateBack = navController.previousBackStackEntry != null,
                currentScreen = backStackEntry?.destination?.route ?: "Home",
                toFavoriteListScreen = { navController.navigate(CharacterNavDirections.favorites.route) },
                toSettingsScreen = { navController.navigate(MainNavDirections.Settings.route) },
                clearAllFavorites = { favoritesViewModel.clearFavorites() })
        },
        bottomBar = {
            BottomBar(modifier = Modifier.background(MaterialTheme.colors.primary),
                bottomBarState = bottomBarState,
                toCharactersScreen = {
                    navController.popBackStack()
                    navController.navigate(MainNavDirections.Characters.route)
                },
                toEpisodesScreen = {
                    navController.popBackStack()
                    navController.navigate(MainNavDirections.Episodes.route)
                },
                toLocationsScreen = {
                    navController.popBackStack()
                    navController.navigate(MainNavDirections.Locations.route)
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
            NetworkConnectionBox()
            MainNavGraph(navController = navController)
        }
    }
}

//Column(
//modifier = Modifier.fillMaxSize(),
//verticalArrangement = Arrangement.Center,
//horizontalAlignment = Alignment.CenterHorizontally
//) {
//    CircularProgressIndicator()
//    Text(
//        text = "Try connecting a network",
//        fontSize = 18.sp,
//        color = Color.Gray,
//        modifier = Modifier.padding(top = 16.dp)
//    )
//}