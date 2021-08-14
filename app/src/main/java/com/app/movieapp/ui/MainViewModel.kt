package com.app.movieapp.ui

import androidx.lifecycle.*
import com.app.movieapp.api.MovieRemoteDataSource
import com.app.movieapp.repository.MovieRepository
import com.app.movieapp.utils.NetworkStatus
import com.app.movieapp.utils.Resource
import com.app.movieapp.model.Movie

class MainViewModel constructor(
    private val repository: MovieRepository
): ViewModel() {

    private var _movieList = MutableLiveData<Resource<List<Movie>>>()

    val movieList: LiveData<Resource<List<Movie>>>
        get() = _movieList

    init{
        getMovieList()
    }

    fun  getMovieList(){
        _movieList.value = Resource( NetworkStatus.LOADING)
        val response = object: MovieRemoteDataSource.NetworkResponse<List<Movie>> {
            override fun onResponse(value: Resource<List<Movie>>) {
                _movieList.value = value
            }
        }
        repository.getMovieList(response)
    }
}