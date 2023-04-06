package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.navigation.character.CharacterNavDirections
import org.koin.androidx.compose.get

@Composable
fun ListRow(left: Character, right: Character?, navController: NavHostController) {
    Row {
        CharacterCard(
            character = left, modifier = Modifier
                .weight(1f),
            onCardClick = { navController.navigate(CharacterNavDirections.details(left.id).route) }
        )
        if (right != null) {
            Spacer(modifier = Modifier.width(16.dp))
            CharacterCard(
                character = right, modifier = Modifier
                    .weight(1f),
                onCardClick = { navController.navigate(CharacterNavDirections.details(right.id).route) }
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}