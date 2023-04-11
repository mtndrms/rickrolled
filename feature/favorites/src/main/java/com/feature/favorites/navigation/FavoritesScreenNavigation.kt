package com.feature.favorites.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.favorites.FavoritesScreen

const val settingsScreenRoute = "favorites"

fun NavController.navigateToFavoritesScreen(navOptions: NavOptions? = null) {
    this.navigate(settingsScreenRoute, navOptions)
}

fun NavGraphBuilder.favoritesScreen() {
    composable(route = settingsScreenRoute) {
        FavoritesScreen(onCardClick = { println("cardClicked") })
    }
}