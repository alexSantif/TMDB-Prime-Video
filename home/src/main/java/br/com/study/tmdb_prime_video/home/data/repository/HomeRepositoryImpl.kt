package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.network.HomeApi

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {

    override suspend fun getPopularMovies(): AppResult<MovieResponse?> {
        return try {
            val response = api.getPopularMovies()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getNowPlayingMovies(): AppResult<MovieResponse?> {
        return try {
            val response = api.getNowPlayingMovies()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getUpcomingMovies(): AppResult<MovieResponse?> {
        return try {
            val response = api.getUpcomingMovies()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getTopRatedMovies(): AppResult<MovieResponse?> {
        return try {
            val response = api.getTopRatedMovies()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getOnTheAirSeries(): AppResult<MovieResponse?> {
        return try {
            val response = api.getOnTheAirSeries()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getPopularSeries(): AppResult<MovieResponse?> {
        return try {
            val response = api.getPopularSeries()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getTopRatedSeries(): AppResult<MovieResponse?> {
        return try {
            val response = api.getTopRatedSeries()
            if (response.isSuccessful) {
                AppResult.Success(response.body())
            } else {
                AppResult.Error(Exception(response.message()))
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}