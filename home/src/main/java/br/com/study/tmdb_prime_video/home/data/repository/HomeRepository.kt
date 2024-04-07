package br.com.study.tmdb_prime_video.home.data.repository

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MoviesResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse

interface HomeRepository {

    suspend fun getMovies(): AppResult<MoviesResponse>

    suspend fun getSearchMovie(text: String?): AppResult<SearchResponse>
}