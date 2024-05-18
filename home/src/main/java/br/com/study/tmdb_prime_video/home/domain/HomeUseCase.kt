package br.com.study.tmdb_prime_video.home.domain

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getPopularMovies(): MovieResponse? {
        return when (val moviesResult = repository.getPopularMovies()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getNowPlayingMovies(): MovieResponse? {
        return when (val moviesResult = repository.getNowPlayingMovies()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getUpcomingMovies(): MovieResponse? {
        return when (val moviesResult = repository.getUpcomingMovies()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getTopRatedMovies(): MovieResponse? {
        return when (val moviesResult = repository.getTopRatedMovies()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getOnTheAirSeries(): MovieResponse? {
        return when (val moviesResult = repository.getOnTheAirSeries()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getPopularSeries(): MovieResponse? {
        return when (val moviesResult = repository.getPopularSeries()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }

    suspend fun getTopRatedSeries(): MovieResponse? {
        return when (val moviesResult = repository.getTopRatedSeries()) {
            is AppResult.Success -> {
                moviesResult.value
            }
            else -> null
        }
    }
}