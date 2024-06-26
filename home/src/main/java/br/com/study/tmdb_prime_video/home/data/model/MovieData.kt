package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieData(

    @SerializedName("id") val id: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("poster_path") val posterPath: String? = null,
    @SerializedName("backdrop_path") val backdropPath: String? = null,
    @SerializedName("release_date") val releaseDate: String? = null
)