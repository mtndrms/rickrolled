package com.example.rickrolled.ui.screen.episode_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.rickrolled.data.entity.Episode
import com.example.rickrolled.ui.screen.episode_list.components.EpisodeCard

@Composable
fun EpisodeListScreen(navController: NavHostController, viewModel: EpisodeListViewModel) {
    val episodes: LazyPagingItems<Episode> = viewModel.data.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn(contentPadding = PaddingValues(16.dp), modifier = Modifier.fillMaxSize()) {
            items(episodes.itemCount) { index ->
                episodes[index]?.let { episode ->
                    EpisodeCard(episode)
                }
            }
        }
    }
}