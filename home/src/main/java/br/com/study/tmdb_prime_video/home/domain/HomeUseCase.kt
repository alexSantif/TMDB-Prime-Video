package br.com.study.tmdb_prime_video.home.domain

import br.com.study.tmdb_prime_video.core.api.AppResult
import br.com.study.tmdb_prime_video.home.data.model.MovieModel
import br.com.study.tmdb_prime_video.home.data.model.MovieModelData
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.data.repository.HomeRepository

class HomeUseCase(private val repository: HomeRepository) {

    suspend fun getPopularMovies(): MovieModel? {
        return when (val moviesResult = repository.getPopularMovies()) {
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
        if (movies is MovieResponse) {
            movies.results?.forEach { item ->
                moviesList.add(
                    MovieModelData(
                        id = item.id,
                        releaseDate = item.releaseDate,
                        title = item.title
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