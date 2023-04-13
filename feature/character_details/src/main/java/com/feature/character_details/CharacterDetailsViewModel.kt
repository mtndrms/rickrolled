package com.feature.character_details

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.network.remote.response.character.Character
import com.core.network.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharacterDetailsViewModel(
    private val repository: CharacterRepository,
    id: Int?
) : ViewModel() {
    var character = mutableStateOf<Character?>(null)

    init {
        fetchCharacterData(id)
    }

    private fun fetchCharacterData(id: Int?) {
        viewModelScope.launch {
            character.value = id?.let {
                repository.getCharacter(it)
            }
        }
    }
}