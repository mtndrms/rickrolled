package com.feature.location_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.location_list.LocationScreen

const val locationListScreenRoute = "locations"

fun NavController.navigateToLocationList(navOptions: NavOptions? = null) {
    this.navigate(locationListScreenRoute, navOptions)
}

fun NavGraphBuilder.locationListScreen() {
    composable(route = locationListScreenRoute) {
        LocationScreen()
    }
}