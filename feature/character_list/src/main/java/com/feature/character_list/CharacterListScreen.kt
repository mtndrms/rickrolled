package com.feature.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.designsystem.component.SearchBar
import com.core.network.entity.Character
import com.core.ui.ListRow
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterListScreen(
    isFavorite: (id: Int) -> Boolean,
    addFavorite: (id: Int) -> Unit,
    removeFavorite: (id: Int) -> Unit,
    onCardClick: (id: Int) -> Unit
) {
    val viewModel: CharacterListViewModel = getViewModel()
    val characters: LazyPagingItems<Character> = viewModel.data.collectAsLazyPagingItems()
    val isSearching by remember { viewModel.isSearching }
    val filteredList by remember { viewModel.filteredList }
    val lazyListState = rememberLazyListState()

    Column {
        SearchBar(
            modifier = Modifier.background(MaterialTheme.colors.primary),
            lazyListState.isScrolled
        ) { query ->
            viewModel.search(query)
        }

        if (!isSearching) {
            CharacterList(
                characters = characters,
                lazyListState = lazyListState,
                isFavorite = isFavorite,
                addFavorite = addFavorite,
                removeFavorite = removeFavorite,
                onCardClick = onCardClick
            )
        } else {
            LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
                items(if (filteredList.size % 2 == 0) filteredList.size / 2 else (filteredList.size / 2) + 1) { index ->
                    ListRow(
                        filteredList[index * 2],
                        if ((index * 2) + 1 < filteredList.size) filteredList[index * 2 + 1] else null,
                        isFavorite = isFavorite,
                        addFavorite = addFavorite,
                        removeFavorite = removeFavorite,
                        onCardClick = onCardClick
                    )
                }
            }
        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0