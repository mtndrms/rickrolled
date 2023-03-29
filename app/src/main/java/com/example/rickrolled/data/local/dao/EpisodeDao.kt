package com.example.rickrolled.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickrolled.data.entity.Episode

@Dao
interface EpisodeDao {
    @Query("SELECT * FROM episode")
    fun getAll(): PagingSource<Int, Episode>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(episodes: List<Episode>)
}