package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class SearchData(

    @SerializedName("id") val id: String? = null,
    @SerializedName("resultType") val resultType: String? = null,
    @SerializedName("image") val image: String? = null,
    @SerializedName("title") val title: String? = null,
    @SerializedName("description") val description: String? = null
)