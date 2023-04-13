package com.feature.favorites

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.ui.ListRow
import org.koin.androidx.compose.getViewModel

@Composable
fun FavoritesScreen(onCardClick: (id: Int) -> Unit) {
    val viewModel: FavoritesViewModel = getViewModel()
    val favorites by remember { viewModel.favorites }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (favorites.isNotEmpty()) {
            LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
                items(if (favorites.size % 2 == 0) favorites.size / 2 else (favorites.size / 2) + 1) { index ->
                    ListRow(
                        favorites[index * 2],
                        if (favorites.size >= index * 2 + 2) favorites[(index * 2) + 1] else null,
                        removeFavorite = viewModel::removeFavorite,
                        addFavorite = viewModel::addFavorite,
                        isFavorite = viewModel::isFavorite,
                        onCardClick = onCardClick
                    )
                }
            }
        } else {
            Text(text = "Burada bir şey yok...", fontSize = 18.sp, color = Color.Gray)
        }
    }
}