package com.example.rickrolled.ui.screen.character_list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.ui.screen.character_list.components.ListRow
import com.example.rickrolled.ui.components.SearchBar

@Composable
fun CharacterListScreen(navController: NavHostController, viewModel: CharacterListViewModel) {
    val characters: LazyPagingItems<Character> = viewModel.data.collectAsLazyPagingItems()
    val isSearching by remember { viewModel.isSearching }
    val filteredList by remember { viewModel.filteredList }

    Column {
        SearchBar(modifier = Modifier.background(MaterialTheme.colors.primary)) {
            viewModel.search(it)
        }

        LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
            if (!isSearching) {
                items(if (characters.itemCount % 2 == 0) characters.itemCount / 2 else (characters.itemCount / 2) + 1) { index ->
                    characters[index * 2]?.let { left ->
                        characters[(index * 2) + 1]?.let { right ->
                            ListRow(
                                left,
                                right,
                                onCardClick = {
                                    navController.navigate(CharacterNavDirections.details(left.id).route)
                                }
                            )
                        }
                    }
                }
            } else {
                items(if (filteredList.size % 2 == 0) filteredList.size / 2 else (filteredList.size / 2) + 1) { index ->
                    ListRow(
                        filteredList[index * 2],
                        if ((index * 2) + 1 < filteredList.size) filteredList[index * 2 + 1] else null,
                        onCardClick = {
                            navController.navigate(CharacterNavDirections.details(filteredList[index * 2].id).route)
                        }
                    )
                }
            }
        }
    }
}