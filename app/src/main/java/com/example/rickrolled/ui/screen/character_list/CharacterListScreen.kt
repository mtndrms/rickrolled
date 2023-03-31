package com.example.rickrolled.ui.screen.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.ui.components.SearchBar
import com.example.rickrolled.ui.screen.character_list.components.CharactersContent
import com.example.rickrolled.ui.screen.character_list.components.SearchContent

@Composable
fun CharacterListScreen(navController: NavHostController, viewModel: CharacterListViewModel) {
    val characters: LazyPagingItems<Character> = viewModel.data.collectAsLazyPagingItems()
    val isSearching by remember { viewModel.isSearching }
    val filteredList by remember { viewModel.filteredList }

    Column {
        SearchBar(modifier = Modifier.background(MaterialTheme.colors.primary)) {
            viewModel.search(
                it
            )
        }

        if (isSearching) {
            SearchContent(filteredList, navController)
        } else {
            CharactersContent(characters, navController)
        }
    }
}