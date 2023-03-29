package com.example.rickrolled.ui.screen.episode_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickrolled.data.entity.Episode
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.data.local.remote_mediator.CharacterRemoteMediator
import com.example.rickrolled.data.local.remote_mediator.EpisodeRemoteMediator
import com.example.rickrolled.data.remote.repository.EpisodeRepository
import com.example.rickrolled.utils.Constants
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class EpisodeListViewModel(
    private val repository: EpisodeRepository,
    private val database: AppDatabase
) : ViewModel() {
    private val pagingSourceFactory = { database.episodeDao().getAll() }
    val data = Pager(
        config = PagingConfig(pageSize = Constants.PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = EpisodeRemoteMediator(repository, database),
        pagingSourceFactory = pagingSourceFactory
    ).flow
}