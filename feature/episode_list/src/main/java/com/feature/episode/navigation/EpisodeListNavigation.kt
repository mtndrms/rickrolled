package com.feature.episode.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.episode.EpisodeListScreen

const val episodeListScreenRoute = "episodes"

fun NavController.navigateToEpisodeList(navOptions: NavOptions? = null) {
    this.navigate(episodeListScreenRoute, navOptions)
}

fun NavGraphBuilder.episodeListScreen() {
    composable(route = episodeListScreenRoute) {
        EpisodeListScreen()
    }
}