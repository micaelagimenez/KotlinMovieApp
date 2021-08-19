package com.app.movieapp.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoritesDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addFavorite(favorite:Favorites)

    @Query("SELECT * FROM favorite_table ORDER BY id ASC")
    fun readALlData(): LiveData<List<Favorites>>

    @Delete
    suspend fun deleteFavorite(favorite: Favorites)

    @Query("DELETE FROM favorite_table")
    suspend fun deleteAllFavorites()
}