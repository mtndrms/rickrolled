package com.example.rickrolled.navigation.episode

import androidx.navigation.NamedNavArgument
import com.example.rickrolled.navigation.NavigationCommand

object EpisodeNavDirections {
    val root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "episodes"
    }

    val episodeList = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "episode_list"
    }
}