package com.example.rickrolled.ui.screen.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.rickrolled.repository.CharacterRepository

class CharacterListViewModelFactory(repository: CharacterRepository) :
    ViewModelProvider.Factory {
    private val mRepository: CharacterRepository

    init {
        mRepository = repository
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CharacterListViewModel(mRepository) as T
    }
}