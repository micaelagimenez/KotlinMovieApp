package com.app.movieapp.api

import com.app.movieapp.model.AuthenticationResponse
import com.app.movieapp.utils.Constants.Companion.API_KEY
import com.app.movieapp.model.MovieListResponse
import com.app.movieapp.model.RatingResponse
import retrofit2.Call
import retrofit2.http.*

interface MovieService {

    @GET("/3/authentication/guest_session/new?api_key=${API_KEY}")
    fun getAuthenticated(): Call<AuthenticationResponse>

    @GET("/3/movie/popular?api_key=${API_KEY}")
    fun getMovieList(): Call<MovieListResponse>

    @POST("/3/movie/{id}/rating?api_key=${API_KEY}")
    fun postRating(@Path("id") id:Int, @Query("guest_session_id") session_id:String,
                   @Body body: Map<String, Float>): Call<RatingResponse>
}