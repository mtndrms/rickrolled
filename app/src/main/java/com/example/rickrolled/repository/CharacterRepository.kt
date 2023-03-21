package com.example.rickrolled.repository

import com.example.rickrolled.data.remote.model.CharacterList
import com.example.rickrolled.data.remote.model.Character
import com.example.rickrolled.data.remote.model.Info
import com.example.rickrolled.data.remote.service.RickAndMortyService

class CharacterRepository(private val service: RickAndMortyService) {
    suspend fun getPage(page: Int): CharacterList {
        service.getAllCharacters(page).run {
            val characters: MutableList<Character> = mutableListOf()
            results.forEach {
                characters.add(Character(it.id, it.name, it.image))
            }
            return CharacterList(Info(info.count), characters)
        }
    }

    suspend fun getOne(id: Int): Character {
        service.getOneCharacter(id).run {
            return Character(id, name, image)
        }
    }
}