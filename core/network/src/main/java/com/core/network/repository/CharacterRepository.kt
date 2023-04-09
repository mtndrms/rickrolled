package com.core.network.repository

import com.core.network.entity.Character
import com.core.network.remote.service.CharacterService
import com.core.network.utils.toCharacterList
import com.core.network.remote.response.character.Character as CharacterResponse

class CharacterRepository(private val service: CharacterService) {
    suspend fun getPage(page: Int): List<Character> {
        return service.getAllCharacters(page).results.toCharacterList()
    }

    suspend fun getMultipleCharacters(page: Int): List<Character> {
        return service.getMultipleCharacters(page).results.toCharacterList()
    }

    suspend fun getCharacter(id: Int): CharacterResponse {
        return service.getCharacter(id)
    }
}