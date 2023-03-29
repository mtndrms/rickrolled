package com.example.rickrolled.navigation.character

import androidx.navigation.NamedNavArgument
import com.example.rickrolled.navigation.NavigationCommand

object CharacterNavDirections {
    val root = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "characters"
    }

    val characterList = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "character_list"
    }

    val favorites = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "favorites"
    }

    val details = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "detail"
    }

    fun details(id: Int? = null) = object : NavigationCommand {
        override val arguments = emptyList<NamedNavArgument>()
        override val route = "detail/$id"
    }
}