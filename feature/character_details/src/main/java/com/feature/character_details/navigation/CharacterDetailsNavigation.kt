package com.feature.character_details.navigation

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.feature.character_details.CharacterDetailsScreen

internal const val characterId = "id"

fun NavController.navigateToCharacterDetailScreen(characterId: Int) {
    this.navigate("character_detail/$characterId")
}

fun NavGraphBuilder.characterDetailScreen() {
    composable(
        route = "character_detail/{id}",
        arguments = listOf(
            navArgument("id") { type = NavType.IntType },
        ),
    ) {
        val id = remember { it.arguments?.getInt("id") }
        CharacterDetailsScreen(id)
    }
}