package com.example.rickrolled.navigation.character

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.*
import com.example.rickrolled.ui.screen.character_details.CharacterDetailsScreen
import com.example.rickrolled.ui.screen.character_list.CharacterListScreen
import com.example.rickrolled.ui.screen.favorites.FavoritesScreen
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.characterGraph(navController: NavHostController) {
    navigation(
        startDestination = CharacterNavDirections.characterList.route,
        route = CharacterNavDirections.root.route
    ) {
        composable(
            route = CharacterNavDirections.characterList.route
        ) {
            CharacterListScreen(navController = navController, viewModel = getViewModel())
        }

        composable(
            route = CharacterNavDirections.favorites.route
        ) {
            FavoritesScreen(navController = navController, viewModel = getViewModel())
        }

        composable(
            route = CharacterNavDirections.details.route + "/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) {
            val id = remember { it.arguments?.getInt("id") }
            CharacterDetailsScreen(id)
        }
    }
}