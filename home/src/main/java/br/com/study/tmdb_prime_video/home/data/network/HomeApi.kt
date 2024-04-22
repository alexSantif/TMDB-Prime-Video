package br.com.study.tmdb_prime_video.home.data.network

import br.com.study.tmdb_prime_video.core.BuildConfig
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface HomeApi {

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$POPULAR_MOVIES$")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("$SEARCH_MOVIE$/{$SEARCH_TEXT}")
    suspend fun getSearchMovie(@Path(SEARCH_TEXT) text: String?): Response<SearchResponse>

    companion object {

        private const val POPULAR_MOVIES = "movie/popular?language=en-US&page=1"
        private const val SEARCH_MOVIE = "searchmovie/"
        private const val SEARCH_TEXT = "text"
    }
}