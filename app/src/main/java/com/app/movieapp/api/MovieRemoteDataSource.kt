package com.app.movieapp.api

import com.app.movieapp.utils.NetworkStatus
import com.app.movieapp.utils.Resource
import com.app.movieapp.model.Movie
import com.app.movieapp.model.MovieListResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRemoteDataSource {
    fun getMovieList(networkResponse: NetworkResponse<List<Movie>>) {
        val service = RetrofitService.instance
            .create(MovieService::class.java)
            .getMovieList()

        service.enqueue(object : Callback<MovieListResponse> {
            override fun onResponse(
                call: Call<MovieListResponse>,
                response: Response<MovieListResponse>
            ) {
                val resource = response.body()?.run {
                    if (results.isNotEmpty())
                        Resource(NetworkStatus.SUCCESS, results)
                    else
                        Resource(NetworkStatus.ERROR)
                } ?: run {
                    Resource(NetworkStatus.ERROR)
                }
                networkResponse.onResponse(resource)
            }

            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
                networkResponse.onResponse(Resource(NetworkStatus.ERROR, message = t.message))
            }
        })
    }

    interface NetworkResponse<T> {
        fun onResponse(value: Resource<T>)
    }
}