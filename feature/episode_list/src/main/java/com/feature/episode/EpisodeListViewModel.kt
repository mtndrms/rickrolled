package com.feature.episode

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.core.network.local.AppDatabase
import com.core.network.local.remote_mediator.EpisodeRemoteMediator
import com.core.network.repository.EpisodeRepository
import com.core.network.utils.Constants.PAGE_SIZE

@OptIn(ExperimentalPagingApi::class)
class EpisodeListViewModel(
    private val repository: EpisodeRepository,
    private val database: AppDatabase
) : ViewModel() {
    private val pagingSourceFactory = { database.episodeDao().getAll() }
    val data = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
        remoteMediator = EpisodeRemoteMediator(repository, database),
        pagingSourceFactory = pagingSourceFactory
    ).flow
}