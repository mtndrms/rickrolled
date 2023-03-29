package com.example.rickrolled.navigation

import androidx.compose.animation.*
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.navigation.character.characterGraph
import com.example.rickrolled.navigation.episode.EpisodeNavDirections
import com.example.rickrolled.navigation.episode.episodeGraph
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(navController: NavHostController) {
    AnimatedNavHost(
        navController = navController,
        startDestination = CharacterNavDirections.root.route
    ) {
        characterGraph(navController)
        episodeGraph(navController)
    }
}