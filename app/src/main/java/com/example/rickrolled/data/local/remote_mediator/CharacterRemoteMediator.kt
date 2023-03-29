package com.example.rickrolled.data.local.remote_mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.data.entity.RemoteKey
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.data.remote.repository.CharacterRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val repository: CharacterRepository,
    private val database: AppDatabase
) : RemoteMediator<Int, Character>() {
    private val characterDao = database.characterDao()
    private val remoteKeyDao = database.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Character>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKey = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKey?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKey = getRemoteKeyForFirstItem(state)
                    val previousPage = remoteKey?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    previousPage
                }

                LoadType.APPEND -> {
                    val remoteKey = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKey?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKey != null
                        )
                    nextPage
                }
            }

            coroutineScope {
                delay(5000)
            }

            val response = repository.getPage(page = currentPage)
            val endOfPaginationReached = response.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage.minus(1)
            val nextPage = if (endOfPaginationReached) null else currentPage.plus(1)

            database.withTransaction {
                val keys = response.map {
                    RemoteKey(
                        entityID = it.id,
                        category = "character",
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                remoteKeyDao.insertAll(keys)
                characterDao.insertAll(response)
            }

            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Character>
    ): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteKeyDao.getOne(category = CATEGORY_NAME, entityID = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Character>
    ): RemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let {
                remoteKeyDao.getOne(category = CATEGORY_NAME, entityID = it.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Character>
    ): RemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let {
                remoteKeyDao.getOne(category = CATEGORY_NAME, entityID = it.id)
            }
    }

    companion object {
        private const val CATEGORY_NAME = "character"
    }
}