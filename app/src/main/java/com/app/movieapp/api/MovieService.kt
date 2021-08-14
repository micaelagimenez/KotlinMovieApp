package com.app.movieapp.api

import com.app.movieapp.utils.Constants.Companion.API_KEY
import com.app.movieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.http.*

interface MovieService {
    @GET("/3/movie/popular?api_key=${API_KEY}")
    fun getMovieList(): Call<MovieListResponse>
}