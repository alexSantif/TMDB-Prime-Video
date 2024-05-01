package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse

interface HomeRepository {

    suspend fun getPopularMovies(): AppResult<MovieResponse?>

    suspend fun getNowPlayingMovies(): AppResult<MovieResponse?>

    suspend fun getUpcomingMovies(): AppResult<MovieResponse?>

    suspend fun getTopRatedMovies(): AppResult<MovieResponse?>
}