package com.feature.character_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.character_list.CharacterListScreen

const val characterListScreenRoute = "character_list"

fun NavController.navigateToCharacterList(navOptions: NavOptions? = null) {
    this.navigate(characterListScreenRoute, navOptions)
}

fun NavGraphBuilder.characterListScreen() {
    composable(route = characterListScreenRoute) {
        CharacterListScreen()
    }
}
