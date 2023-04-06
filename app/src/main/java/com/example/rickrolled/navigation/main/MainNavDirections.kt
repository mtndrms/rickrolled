package com.example.rickrolled.navigation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class MainNavDirections(val route: String, val title: String, val icon: ImageVector) {
    object Characters : MainNavDirections("characters", "Characters", Icons.Outlined.Person)
    object Episodes : MainNavDirections("episodes", "Episodes", Icons.Outlined.List)
    object Locations : MainNavDirections("locations", "Locations", Icons.Outlined.LocationOn)
    object Settings : MainNavDirections("settings", "Settings", Icons.Outlined.Settings)
}
