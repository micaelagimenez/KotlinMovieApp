package com.app.movieapp.repository

import com.app.movieapp.api.MovieRemoteDataSource
import com.app.movieapp.model.Movie

class MovieRepository constructor(
    private val remoteDataSource: MovieRemoteDataSource
) {
    fun getMovieList(networkResponse: MovieRemoteDataSource.NetworkResponse<List<Movie>>){
        return remoteDataSource.getMovieList(networkResponse)
    }
}