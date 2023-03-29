package com.example.rickrolled.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class Character(
    @PrimaryKey(autoGenerate = false) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url") val imageURL: String,
    @ColumnInfo(name = "prev_page") val prevPage: Int?,
    @ColumnInfo(name = "next_page") val nextPage: Int?
)
