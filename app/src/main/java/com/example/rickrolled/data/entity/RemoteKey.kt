package com.example.rickrolled.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_key")
data class RemoteKey(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "entity_id") val entityID: Int,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "prev_page") val prevPage: Int?,
    @ColumnInfo(name = "next_page") val nextPage: Int?
)
