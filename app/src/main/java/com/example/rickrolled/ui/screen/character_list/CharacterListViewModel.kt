package com.example.rickrolled.ui.screen.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.rickrolled.data.CharacterPagingSource
import com.example.rickrolled.repository.CharacterRepository

class CharacterListViewModel(
    private val characterRepository: CharacterRepository,
) : ViewModel() {
    val data = Pager(
        PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
    ) {
        CharacterPagingSource(characterRepository)
    }.flow.cachedIn(viewModelScope)
}