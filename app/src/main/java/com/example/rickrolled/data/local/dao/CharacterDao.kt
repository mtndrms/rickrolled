package com.example.rickrolled.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.example.rickrolled.data.entity.Character

@Dao
interface CharacterDao {
    @Query("SELECT * FROM character")
    fun getAll(): PagingSource<Int, Character>

    @Query("SELECT * FROM character")
    suspend fun getAllAsList(): List<Character>

    @Query("SELECT * FROM character WHERE id IN (:filterValues)")
    suspend fun getMultipleCharacter(filterValues: List<String>): List<Character>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(characters: List<Character>)

    @Delete
    suspend fun deleteOne(character: Character)

    @Query("DELETE FROM character")
    suspend fun deleteAll()
}