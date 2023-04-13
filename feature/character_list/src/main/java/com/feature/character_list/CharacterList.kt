package com.feature.character_list

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.core.network.entity.Character
import com.core.ui.ListRow

@Composable
fun CharacterList(
    characters: LazyPagingItems<Character>,
    lazyListState: LazyListState,
    isFavorite: (id: Int) -> Boolean,
    addFavorite: (id: Int) -> Unit,
    removeFavorite: (id: Int) -> Unit,
    onCardClick: (id: Int) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        modifier = Modifier
            .fillMaxSize(),
        state = lazyListState
    ) {
        items(characters.itemCount / 2) { index ->
            characters[index * 2]?.let { left ->
                ListRow(
                    left,
                    if ((index * 2) + 1 < characters.itemCount) characters[index * 2 + 1] else null,
                    isFavorite = isFavorite,
                    addFavorite = addFavorite,
                    removeFavorite = removeFavorite,
                    onCardClick = onCardClick
                )
            }
        }
    }
}