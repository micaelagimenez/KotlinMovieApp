package com.app.movieapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_table")
data class Favorites(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val poster: String
)