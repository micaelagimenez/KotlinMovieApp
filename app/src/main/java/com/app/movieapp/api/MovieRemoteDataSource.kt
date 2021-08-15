package com.app.movieapp.api

import com.app.movieapp.model.AuthenticationResponse
import com.app.movieapp.utils.NetworkStatus
import com.app.movieapp.utils.Resource
import com.app.movieapp.model.Movie
import com.app.movieapp.model.MovieListResponse
import com.app.movieapp.model.RatingResponse
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


    interface RatingCallBack{
        fun onSuccess()
        fun onError(message: String?)
    }

    fun postRating(rating: Int, id:Map<String,Float>, session_id:String, ratingCallback: RatingCallBack) {
        val service = RetrofitService.instance
            .create(MovieService::class.java)
        val call = service.postRating(rating, session_id, id)

        call.enqueue(object : Callback<RatingResponse?> {

            override fun onResponse(
                call: Call<RatingResponse?>,
                response: Response<RatingResponse?>
            ) {
                if (response.isSuccessful) {
                    ratingCallback.onSuccess()
                } else {
                    ratingCallback.onError(message="Error")
                }
            }

            override fun onFailure(call: Call<RatingResponse?>, t: Throwable) {
                ratingCallback.onError(message="Error")
            }
        })
    }

    fun getAuthenticated(networkResponse: NetworkResponse<AuthenticationResponse>) {
        val service = RetrofitService.instance
            .create(MovieService::class.java)
            .getAuthenticated()

        service.enqueue(object : Callback<AuthenticationResponse> {
            override fun onResponse(
                call: Call<AuthenticationResponse>,
                response: Response<AuthenticationResponse>
            ) {
                val resource = response.body()?.run {
                    if (response.isSuccessful)
                        Resource(NetworkStatus.SUCCESS, this)
                    else
                        Resource(NetworkStatus.ERROR)
                } ?: run {
                    Resource(NetworkStatus.ERROR)
                }
                networkResponse.onResponse(resource)
            }

            override fun onFailure(call: Call<AuthenticationResponse>, t: Throwable) {
                networkResponse.onResponse(Resource(NetworkStatus.ERROR, message = t.message))
            }
        })
    }

    interface NetworkResponse<T> {
        fun onResponse(value: Resource<T>)
    }
}