package com.example.rickrolled.ui.screen.character_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickrolled.ui.screen.character_list.components.ListRow

@Composable
fun CharacterListScreen(viewModel: CharacterListViewModel) {
    val characters = viewModel.data.collectAsLazyPagingItems()

    when (characters.loadState.refresh) {
        // first load
        LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        // when first load fails
        is LoadState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Something went wrong!",
                    color = Color.Red,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
        else -> {
            LazyColumn(contentPadding = PaddingValues(16.dp)) {
                items(characters.itemCount / 2) { index ->
                    // left column
                    characters[index * 2]?.let { first ->
                        // right column
                        characters[(index * 2) + 1]?.let { second ->
                            ListRow(first = first, second = second)
                        }
                    }
                }
            }
        }
    }

    when (characters.loadState.append) {
        // loading next page
        is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                LinearProgressIndicator(
                    color = Color.Black,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
            }
        }
        else -> {}
    }
}