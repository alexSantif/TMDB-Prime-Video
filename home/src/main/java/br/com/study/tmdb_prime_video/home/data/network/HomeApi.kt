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

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$NOW_PLAYING_MOVIES$")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$UPCOMING_MOVIES$")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$TOP_RATED_MOVIES$")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$ON_THE_AIR_SERIES$")
    suspend fun getOnTheAirSeries(): Response<MovieResponse>

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$POPULAR_SERIES$")
    suspend fun getPopularSeries(): Response<MovieResponse>

    @Headers(BuildConfig.TMDB_BEARER)
    @GET("$TOP_RATED_SERIES$")
    suspend fun getTopRatedSeries(): Response<MovieResponse>

    companion object {

        private const val POPULAR_MOVIES = "movie/popular?language=en-US&page=1"
        private const val NOW_PLAYING_MOVIES = "movie/now_playing?language=en-US&page=1"
        private const val UPCOMING_MOVIES = "movie/upcoming?language=en-US&page=1"
        private const val TOP_RATED_MOVIES = "movie/top_rated?language=en-US&page=1"
        private const val ON_THE_AIR_SERIES = "tv/on_the_air?language=en-US&page=1"
        private const val POPULAR_SERIES = "tv/popular?language=en-US&page=1"
        private const val TOP_RATED_SERIES = "movie/top_rated?language=en-US&page=1"
    }
}