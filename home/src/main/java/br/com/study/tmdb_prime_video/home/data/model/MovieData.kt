package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class MovieData(

    @SerializedName("id") val id: String? = null,
    @SerializedName("rank") val rank: String? = null,
    @SerializedName("rankUpDown") val rankUpDown: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("fullTitle") val fullTitle: String? = null,
    @SerializedName("year") val year: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("crew") val crew: String? = null,
    @SerializedName("imDbRating") val imDbRating: String? = null,
    @SerializedName("imDbRatingCount") val imDbRatingCount: String? = null
)