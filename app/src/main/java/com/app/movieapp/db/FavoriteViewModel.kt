package com.app.movieapp.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<Favorites>>
    private val repository: FavoritesRepository

    init {
        val favoriteDao = FavoriteDatabase.getDatabase(application).favoriteDao()
        repository = FavoritesRepository(favoriteDao)
        readAllData = repository.readAllData
    }

    fun addFavorite(favorite:Favorites){
        viewModelScope.launch(Dispatchers.IO){
            repository.addFavorite(favorite)
        }
    }

    fun deleteFavorite(favorite:Favorites){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteFavorite(favorite)
        }
    }

    fun deleteAllFavorites(){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAllFavorites()
        }
    }
}