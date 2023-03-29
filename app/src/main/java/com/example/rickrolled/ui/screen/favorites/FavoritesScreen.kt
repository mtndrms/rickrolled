package com.example.rickrolled.ui.screen.favorites

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.ui.screen.character_list.components.ListRow

@Composable
fun FavoritesScreen(
    navController: NavHostController,
    viewModel: FavoritesViewModel
) {
    val favorites by remember { viewModel.favorites }

    Column {
        LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
            items(if (favorites.size % 2 == 0) favorites.size / 2 else (favorites.size / 2) + 1) { index ->
                ListRow(
                    favorites[index * 2],
                    if (favorites.size >= index * 2 + 2) {
                        favorites[(index * 2) + 1]
                    } else {
                        null
                    },
                    onCardClick = { navController.navigate(CharacterNavDirections.details(favorites[index * 2].id).route) }
                )
            }
        }
    }
}