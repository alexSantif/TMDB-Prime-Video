package br.com.study.tmdb_prime_video.home.data.network

import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("$POPULAR_MOVIES$")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("$NOW_PLAYING_MOVIES$")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("$UPCOMING_MOVIES$")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("$TOP_RATED_MOVIES$")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("$ON_THE_AIR_SERIES$")
    suspend fun getOnTheAirSeries(): Response<MovieResponse>

    @GET("$POPULAR_SERIES$")
    suspend fun getPopularSeries(): Response<MovieResponse>

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