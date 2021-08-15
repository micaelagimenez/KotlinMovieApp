package com.app.movieapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.movieapp.api.MovieRemoteDataSource

class RatingViewModelFactory: ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val remoteDataSource = MovieRemoteDataSource()

        return RatingViewModel(remoteDataSource) as T
    }
}