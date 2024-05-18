package br.com.study.tmdb_prime_video.home.data.network

import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApi {

    @GET("$MOVIE$POPULAR_MOVIES$QUERY_PARAMETERS")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("$MOVIE$NOW_PLAYING_MOVIES$QUERY_PARAMETERS")
    suspend fun getNowPlayingMovies(): Response<MovieResponse>

    @GET("$MOVIE$UPCOMING_MOVIES$QUERY_PARAMETERS")
    suspend fun getUpcomingMovies(): Response<MovieResponse>

    @GET("$MOVIE$TOP_RATED_MOVIES$QUERY_PARAMETERS")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("$MOVIE$ON_THE_AIR_SERIES$QUERY_PARAMETERS")
    suspend fun getOnTheAirSeries(): Response<MovieResponse>

    @GET("$MOVIE$POPULAR_SERIES$QUERY_PARAMETERS")
    suspend fun getPopularSeries(): Response<MovieResponse>

    @GET("$MOVIE$TOP_RATED_SERIES$QUERY_PARAMETERS")
    suspend fun getTopRatedSeries(): Response<MovieResponse>

    companion object {

        private const val QUERY_PARAMETERS = "?language=en-US&page=1"
        private const val MOVIE = "movie/"
        private const val POPULAR_MOVIES = "popular"
        private const val NOW_PLAYING_MOVIES = "now_playing"
        private const val UPCOMING_MOVIES = "upcoming"
        private const val TOP_RATED_MOVIES = "top_rated"
        private const val ON_THE_AIR_SERIES = "on_the_air"
        private const val POPULAR_SERIES = "popular"
        private const val TOP_RATED_SERIES = "top_rated"
    }
}