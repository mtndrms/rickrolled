package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import com.core.network.entity.Character

@Composable
fun CharactersContent(
    characters: LazyPagingItems<Character>,
    navController: NavHostController,
    lazyListState: LazyListState
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
                    navController
                )
            }
        }
    }
}