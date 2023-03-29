package com.example.rickrolled.data.remote.repository

import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.data.remote.service.CharacterService
import com.example.rickrolled.data.remote.response.character.Character as CharacterResponse

class CharacterRepository(
    private val service: CharacterService,
    private val database: AppDatabase
) {
    suspend fun getPage(page: Int): List<Character> {
        val characters: MutableList<Character> = mutableListOf()
        service.getAllCharacters(page).run {
            results.forEach {
                characters.add(
                    Character(
                        it.id,
                        it.name,
                        it.image,
                        prevPage = if (page == 1) null else {
                            page.minus(1)
                        },
                        nextPage = if (page == this.info.pages) null else {
                            page.plus(1)
                        }
                    )
                )
            }
        }
        return characters
    }

    suspend fun getMultipleCharacters(page: Int): List<Character> {
        val characters: MutableList<Character> = mutableListOf()
        service.getMultipleCharacters(page).run {
            results.forEach {
                characters.add(
                    Character(
                        it.id,
                        it.name,
                        it.image,
                        prevPage = if (page == 1) null else {
                            page.minus(1)
                        },
                        nextPage = if (page == this.info.pages) null else {
                            page.plus(1)
                        }
                    )
                )
            }
        }
        return characters
    }

    suspend fun getCharacter(id: Int): CharacterResponse {
        return service.getCharacter(id)
    }
}