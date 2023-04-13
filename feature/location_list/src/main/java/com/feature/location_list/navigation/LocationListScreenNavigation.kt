package com.feature.location_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.location_list.LocationListScreen

const val locationListScreenRoute = "location_list"

fun NavController.navigateToLocationListScreen(navOptions: NavOptions? = null) {
    this.navigate(locationListScreenRoute, navOptions)
}

fun NavGraphBuilder.locationListScreen() {
    composable(route = locationListScreenRoute) {
        LocationListScreen()
    }
}