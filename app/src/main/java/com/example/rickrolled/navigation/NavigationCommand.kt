package com.example.rickrolled.navigation

import androidx.navigation.NamedNavArgument

interface NavigationCommand {
    val arguments: List<NamedNavArgument>
    val route: String
}