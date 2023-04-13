package com.core.ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.network.entity.Character

@Composable
fun ListRow(
    left: Character,
    right: Character?,
    isFavorite: (id: Int) -> Boolean,
    addFavorite: (id: Int) -> Unit,
    removeFavorite: (id: Int) -> Unit,
    onCardClick: (id: Int) -> Unit
) {
    Row {
        CharacterCard(
            character = left,
            modifier = Modifier
                .weight(1f),
            isFavorite = isFavorite,
            addFavorite = addFavorite,
            removeFavorite = removeFavorite,
            onCardClick = onCardClick
        )
        if (right != null) {
            Spacer(modifier = Modifier.width(16.dp))
            CharacterCard(
                character = right,
                modifier = Modifier
                    .weight(1f),
                isFavorite = isFavorite,
                addFavorite = addFavorite,
                removeFavorite = removeFavorite,
                onCardClick = onCardClick
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}