package com.feature.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.designsystem.component.CharactersContent
import com.core.designsystem.component.SearchBar
import com.core.designsystem.component.SearchContent
import com.core.network.entity.Character
import org.koin.androidx.compose.getViewModel

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel = getViewModel()) {
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

//        if (isSearching) {
//            SearchContent(filteredList)
//        } else {
//            CharactersContent(characters, lazyListState)
//        }
    }
}

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > 0