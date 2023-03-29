package com.example.rickrolled.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickrolled.data.entity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Query("SELECT * FROM remote_key WHERE category = :category and entity_id = :entityID")
    suspend fun getOne(category: String, entityID: Int): RemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKeys: List<RemoteKey>)

    @Query("DELETE FROM remote_key WHERE category = :category")
    suspend fun deleteAll(category: String)
}