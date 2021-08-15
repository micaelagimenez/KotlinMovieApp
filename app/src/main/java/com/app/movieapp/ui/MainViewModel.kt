package com.app.movieapp.ui

import android.util.Log
import androidx.lifecycle.*
import com.app.movieapp.api.MovieRemoteDataSource
import com.app.movieapp.model.AuthenticationResponse
import com.app.movieapp.repository.MovieRepository
import com.app.movieapp.utils.NetworkStatus
import com.app.movieapp.utils.Resource
import com.app.movieapp.model.Movie

class MainViewModel constructor(
    private val repository: MovieRepository
): ViewModel() {

    private var _movieList = MutableLiveData<Resource<List<Movie>>>()
    private var _session = MutableLiveData<Resource<AuthenticationResponse>>()

    val movieList: LiveData<Resource<List<Movie>>>
        get() = _movieList

    val session: LiveData<Resource<AuthenticationResponse>>
        get() = _session

    init{
        getMovieList()
        getAuthenticated()
    }

    private fun  getMovieList(){
        _movieList.value = Resource( NetworkStatus.LOADING)
        val response = object: MovieRemoteDataSource.NetworkResponse<List<Movie>> {
            override fun onResponse(value: Resource<List<Movie>>) {
                _movieList.value = value
            }
        }
        repository.getMovieList(response)
    }

    private fun getAuthenticated(){
        _session.value = Resource(NetworkStatus.LOADING)
        val response = object: MovieRemoteDataSource.NetworkResponse<AuthenticationResponse> {
            override fun onResponse(value: Resource<AuthenticationResponse>) {
                _session.value = value
                //authentication log for status
                Log.d("Authentication status: ", "$value")
            }
        }
        repository.getAuthenticated(response)
    }
}