package com.core.designsystem.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.core.network.entity.Character

@Composable
fun SearchContent(filteredList: List<Character>) {
    LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
        items(filteredList.size / 2) { index ->
            ListRow(
                filteredList[index * 2],
                if ((index * 2) + 1 < filteredList.size) filteredList[index * 2 + 1] else null,
                isFavorite = true,
                removeFavorite = {},
                addFavorite = {},
                isNetworkAvailable = true
            )
        }
    }
}