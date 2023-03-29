package com.example.rickrolled.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.data.entity.Episode
import com.example.rickrolled.data.entity.RemoteKey
import com.example.rickrolled.data.local.dao.CharacterDao
import com.example.rickrolled.data.local.dao.EpisodeDao
import com.example.rickrolled.data.local.dao.RemoteKeyDao

@Database(entities = [Character::class, Episode::class, RemoteKey::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun remoteKeyDao(): RemoteKeyDao
    abstract fun episodeDao(): EpisodeDao
}
