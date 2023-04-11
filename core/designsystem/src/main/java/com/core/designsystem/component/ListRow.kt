package com.core.designsystem.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.core.network.entity.Character

@Composable
fun ListRow(
    left: Character,
    right: Character?,
    addFavorite: (id: Int) -> Unit,
    removeFavorite: (id: Int) -> Unit,
    isFavorite: Boolean,
    isNetworkAvailable: Boolean,
) {
    Row {
        CharacterCard(
            character = left, modifier = Modifier
                .weight(1f),
            addFavorite = addFavorite,
            isFavorite = isFavorite,
            removeFavorite = removeFavorite,
            isNetworkAvailable = isNetworkAvailable
        )
        if (right != null) {
            Spacer(modifier = Modifier.width(16.dp))
            CharacterCard(
                character = right, modifier = Modifier
                    .weight(1f),
                addFavorite = addFavorite,
                isFavorite = isFavorite,
                removeFavorite = removeFavorite,
                isNetworkAvailable = isNetworkAvailable
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}