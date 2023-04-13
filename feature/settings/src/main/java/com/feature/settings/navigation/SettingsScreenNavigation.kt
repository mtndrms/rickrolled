package com.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.settings.SettingsScreen

const val settingsScreenRoute = "settings"

fun NavController.navigateToSettingsScreen(navOptions: NavOptions? = null) {
    this.navigate(settingsScreenRoute, navOptions)
}

fun NavGraphBuilder.settingsScreen() {
    composable(route = settingsScreenRoute) {
        SettingsScreen()
    }
}