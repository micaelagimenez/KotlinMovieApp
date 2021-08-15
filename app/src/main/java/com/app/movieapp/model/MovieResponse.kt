package com.app.movieapp.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val results: List<Movie>
)

data class Movie(
    val id: Int,
    val title: String,
    @SerializedName("poster_path") val poster: String,
    val overview: String,
    @SerializedName("original_language") val originalLanguage: String,
    val popularity: Float,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Float
)
