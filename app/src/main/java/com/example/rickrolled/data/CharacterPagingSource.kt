package com.example.rickrolled.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickrolled.repository.CharacterRepository
import com.example.rickrolled.data.remote.model.Character as CharacterModel

class CharacterPagingSource(private val repository: CharacterRepository) :
    PagingSource<Int, CharacterModel>() {
    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {
        return try {
            val currentPageNumber = params.key ?: 1
            val characters = repository.getPage(currentPageNumber).characters

            LoadResult.Page(
                data = characters,
                prevKey = if (currentPageNumber == 1) null else currentPageNumber.minus(1),
                nextKey = if (characters.isEmpty()) null else currentPageNumber.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}