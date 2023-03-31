package com.example.rickrolled.ui.screen.character_list

import android.content.Context
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.data.local.remote_mediator.CharacterRemoteMediator
import com.example.rickrolled.data.remote.repository.CharacterRepository
import com.example.rickrolled.utils.Constants.PAGE_SIZE
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.utils.ConnectivityObserver
import com.example.rickrolled.utils.NetworkConnectivityObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
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