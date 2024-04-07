package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.core.api.handleApiError
import br.com.study.tmdb_prime_video.core.api.handleSuccess
import br.com.study.tmdb_prime_video.home.data.model.MoviesResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse
import br.com.study.tmdb_prime_video.home.data.network.HomeApi

class HomeRepositoryImpl(private val api: HomeApi) : HomeRepository {

    override suspend fun getMovies(): AppResult<MoviesResponse> {
        return try {
            val response = api.getMovies()
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }

    override suspend fun getSearchMovie(text: String?): AppResult<SearchResponse> {
        return try {
            val response = api.getSearchMovie(text)
            if (response.isSuccessful) {
                handleSuccess(response)
            } else {
                handleApiError(response)
            }
        } catch (e: Exception) {
            AppResult.Error(e)
        }
    }
}