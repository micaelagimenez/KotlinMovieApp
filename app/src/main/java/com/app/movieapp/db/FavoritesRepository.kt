package com.app.movieapp.db

import androidx.lifecycle.LiveData

class FavoritesRepository(private val favoritesDao: FavoritesDao) {

    val readAllData: LiveData<List<Favorites>> = favoritesDao.readALlData()

    suspend fun addFavorite(favorite: Favorites){
        favoritesDao.addFavorite(favorite)
    }

    suspend fun deleteFavorite(favorite: Favorites){
        favoritesDao.deleteFavorite(favorite)
    }

    suspend fun deleteAllFavorites(){
        favoritesDao.deleteAllFavorites()
    }
}