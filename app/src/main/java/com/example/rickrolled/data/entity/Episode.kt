package com.example.rickrolled.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "episode")
data class Episode(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "episode") val episode: String,
    @ColumnInfo(name = "air_date") val airDate: String
)
