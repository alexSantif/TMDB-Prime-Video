package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieResponse(

    @SerializedName("page") val page: String? = null,
    @SerializedName("results") val results: List<MovieData>? = null
)