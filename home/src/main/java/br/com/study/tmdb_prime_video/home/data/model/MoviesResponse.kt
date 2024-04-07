package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class MoviesResponse(

    @SerializedName("items") val items: List<MovieData>? = null
)