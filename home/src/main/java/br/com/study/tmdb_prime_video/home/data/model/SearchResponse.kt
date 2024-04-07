package br.com.study.tmdb_prime_video.home.data.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(

    @SerializedName("searchType") val searchType: String? = null,
    @SerializedName("expression") val expression: String? = null,
    @SerializedName("results") val results: List<SearchData>? = null,
    @SerializedName("errorMessage") val errorMessage: String? = null
)