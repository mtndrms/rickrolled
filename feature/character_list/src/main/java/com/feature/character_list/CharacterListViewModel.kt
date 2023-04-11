package com.feature.character_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.core.network.entity.Character
import com.core.network.local.AppDatabase
import com.core.network.local.remote_mediator.CharacterRemoteMediator
import com.core.network.repository.CharacterRepository
import com.core.network.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagingApi::class)
class CharacterListViewModel(
    characterRepository: CharacterRepository,
    private val database: AppDatabase,
) : ViewModel() {
    var filteredList = mutableStateOf<List<Character>>(emptyList())
    val isSearching = mutableStateOf(false)
    private var isStartSearching = true

    fun search(searchQuery: String) {
        isSearching.value = true

        if (isStartSearching) {
            getAllCharacters()
            isStartSearching = false
        }

        viewModelScope.launch(Dispatchers.Default) {
            if (searchQuery.isEmpty()) {
                isSearching.value = false
                isStartSearching = true
                return@launch
            }

            filteredList.value = filteredList.value.filter {
                it.name.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    private fun getAllCharacters() {
        viewModelScope.launch {
            filteredList.value = database.characterDao().getAllAsList()
        }
    }

    private val pagingSourceFactory = { database.characterDao().getAll() }
    val data = Pager(
        config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = true),
        remoteMediator = CharacterRemoteMediator(characterRepository, database),
        pagingSourceFactory = pagingSourceFactory
    ).flow
}