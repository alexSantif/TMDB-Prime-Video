package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse

interface HomeRepository {

    suspend fun getPopularMovies(): AppResult<MovieResponse?>

    suspend fun getNowPlayingMovies(): AppResult<MovieResponse?>

    suspend fun getUpcomingMovies(): AppResult<MovieResponse?>

    suspend fun getTopRatedMovies(): AppResult<MovieResponse?>

    suspend fun getOnTheAirSeries(): AppResult<MovieResponse?>

    suspend fun getPopularSeries(): AppResult<MovieResponse?>

    suspend fun getTopRatedSeries(): AppResult<MovieResponse?>
}