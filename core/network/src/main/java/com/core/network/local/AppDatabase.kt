package com.core.network.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.core.network.entity.Episode
import com.core.network.entity.Character
import com.core.network.entity.RemoteKey
import com.core.network.local.dao.EpisodeDao
import com.core.network.local.dao.CharacterDao
import com.core.network.local.dao.RemoteKeyDao

@Database(entities = [Character::class, Episode::class, RemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun episodeDao(): EpisodeDao
}
