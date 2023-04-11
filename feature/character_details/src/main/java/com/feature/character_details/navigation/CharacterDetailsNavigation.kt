package com.feature.character_details.navigation

import android.net.Uri
import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.feature.character_details.CharacterDetailsScreen

internal const val characterId = "id"

interface StringDecoder {
    fun decodeString(encodedString: String): String
}

fun NavController.navigateToCharacterDetail(characterId: String) {
    val encodedId = Uri.encode(characterId)
    this.navigate("character_details/$encodedId")
}

fun NavGraphBuilder.characterDetailScreen() {
    composable(
        route = "character_details/{$characterId}",
        arguments = listOf(
            navArgument(characterId) { type = NavType.IntType },
        ),
    ) {
        val id = remember { it.arguments?.getInt("id") }
        CharacterDetailsScreen(id)
    }
}