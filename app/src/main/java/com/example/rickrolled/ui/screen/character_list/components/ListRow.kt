package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickrolled.data.entity.Character
import org.koin.androidx.compose.get

@Composable
fun ListRow(left: Character, right: Character?, onCardClick: () -> Unit = {}) {
    Row {
        CharacterCard(
            character = left, modifier = Modifier
                .weight(1f),
            get(),
            onCardClick
        )
        if (right != null) {
            Spacer(modifier = Modifier.width(16.dp))
            CharacterCard(
                character = right, modifier = Modifier
                    .weight(1f),
                get(),
                onCardClick
            )
        } else {
            Spacer(modifier = Modifier.weight(1f))
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}