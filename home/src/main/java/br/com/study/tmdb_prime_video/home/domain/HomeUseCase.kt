package br.com.study.tmdb_prime_video.home.domain

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieModel
import br.com.study.tmdb_prime_video.home.data.model.MovieModelData
import br.com.study.tmdb_prime_video.home.data.model.MoviesResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse
import br.com.study.tmdb_prime_video.home.data.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getMovies(): MovieModel? {
        return when (val moviesResult = repository.getMovies()) {
            is AppResult.Success -> {
                mapResponse(moviesResult.successData)
            }
            is AppResult.Error -> {
                mapError(moviesResult.exception.message)
            }
            else -> null
        }
    }

    suspend fun getSearchMovie(text: String?): MovieModel? {
        return when (val moviesResult = repository.getSearchMovie(text)) {
            is AppResult.Success -> {
                mapResponse(moviesResult.successData)
            }
            is AppResult.Error -> {
                mapError(moviesResult.exception.message)
            }
            else -> null
        }
    }

    private fun mapResponse(movies: Any): MovieModel {
        val moviesList = mutableListOf<MovieModelData>()
        if (movies is MoviesResponse) {
            movies.items?.forEach { item ->
                moviesList.add(
                    MovieModelData(
                        id = item.id,
                        image = item.image,
                        title = item.title
                    )
                )
            }
        } else if (movies is SearchResponse) {
            movies.results?.forEach { result ->
                moviesList.add(
                    MovieModelData(
                        id = result.id,
                        image = result.image,
                        title = result.title
                    )
                )
            }
        }
        return MovieModel(
            data = moviesList
        )
    }

    private fun mapError(error: String?): MovieModel = MovieModel(error = error)
}