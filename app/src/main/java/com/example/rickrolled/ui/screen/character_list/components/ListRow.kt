package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.rickrolled.data.remote.model.Character

@Composable
fun ListRow(first: Character, second: Character) {
    Row {
        CharacterCard(
            entry = first, modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.width(16.dp))
        CharacterCard(
            entry = second, modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
        )
    }
    Spacer(modifier = Modifier.height(16.dp))
}