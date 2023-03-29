package com.example.rickrolled.navigation.episode

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.example.rickrolled.ui.screen.episode_list.EpisodeListScreen
import com.google.accompanist.navigation.animation.composable
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.episodeGraph(navController: NavHostController) {
    navigation(
        startDestination = EpisodeNavDirections.episodeList.route,
        route = EpisodeNavDirections.root.route
    ) {
        composable(
            route = EpisodeNavDirections.episodeList.route
        ) {
            EpisodeListScreen(navController = navController, viewModel = getViewModel())
        }
    }
}