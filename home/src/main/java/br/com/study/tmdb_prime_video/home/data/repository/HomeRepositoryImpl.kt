package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.BuildConfig
import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.core.mock.getNowPlayingMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getOnTheAirSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getPopularMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getPopularSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getTopRatedMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getTopRatedSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getUpcomingMoviesMock
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.network.HomeApi
import com.google.gson.Gson

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {

    override suspend fun getPopularMovies(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getPopularMoviesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getNowPlayingMovies(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getNowPlayingMoviesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getUpcomingMovies(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getUpcomingMoviesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getTopRatedMovies(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getTopRatedMoviesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getOnTheAirSeries(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getOnTheAirSeriesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getPopularSeries(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getPopularSeriesMock(), MovieResponse::class.java))
        } else {
            try {
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
    }

    override suspend fun getTopRatedSeries(): AppResult<MovieResponse?> {
        return if (BuildConfig.BUILD_TYPE == "mock") {
            AppResult.Success(Gson().fromJson(getTopRatedSeriesMock(), MovieResponse::class.java))
        } else {
            try {
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
}