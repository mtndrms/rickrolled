package com.feature.episode

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.core.network.entity.Episode
import com.feature.episode.components.EpisodeCard
import org.koin.androidx.compose.getViewModel

@Composable
fun EpisodeListScreen(viewModel: EpisodeListViewModel = getViewModel()) {
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