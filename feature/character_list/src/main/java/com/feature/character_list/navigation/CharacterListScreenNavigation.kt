package com.feature.character_list.navigation

import androidx.navigation.NavController
import androidx.navigation.NavOptions

const val characterListScreenRoute = "character_list"

fun NavController.navigateToCharacterListScreen(navOptions: NavOptions? = null) {
    this.navigate(characterListScreenRoute, navOptions)
}

//fun NavGraphBuilder.characterListScreen() {
//    composable(route = characterListScreenRoute) {
//        CharacterListScreen()
//    }
//}
