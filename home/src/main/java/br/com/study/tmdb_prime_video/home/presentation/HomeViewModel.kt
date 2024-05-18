package br.com.study.tmdb_prime_video.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study.tmdb_prime_video.core.base.BaseUiState
import br.com.study.tmdb_prime_video.home.data.model.ImagesMoviesModel
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.domain.HomeUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    private val _popularMoviesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val popularMoviesUiState = _popularMoviesUiState.asStateFlow()

    private val _nowPlayingMoviesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val nowPlayingMoviesUiState = _nowPlayingMoviesUiState.asStateFlow()

    private val _upcomingMoviesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val upcomingMoviesUiState = _upcomingMoviesUiState.asStateFlow()

    private val _topRatedMoviesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val topRatedMoviesUiState = _topRatedMoviesUiState.asStateFlow()

    private val _onTheAirSeriesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val onTheAirSeriesUiState = _onTheAirSeriesUiState.asStateFlow()

    private val _popularSeriesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val popularSeriesUiState = _popularSeriesUiState.asStateFlow()

    private val _topRatedSeriesUiState =
        MutableStateFlow(BaseUiState.Success<MovieResponse?>(MovieResponse()))
    val topRatedSeriesUiState = _topRatedSeriesUiState.asStateFlow()

    private val _imagesMoviesUiState =
        MutableStateFlow(BaseUiState.Success<ImagesMoviesModel?>(ImagesMoviesModel()))
    val imagesMoviesUiState = _imagesMoviesUiState.asStateFlow()

    fun getHomeMovies() {
        viewModelScope.launch {
            val popularMovies = async { useCase.getPopularMovies() }
            val nowPlayingMovies = async { useCase.getNowPlayingMovies() }
            val upcomingMovies = async { useCase.getUpcomingMovies() }
            val topRatedMovies = async { useCase.getTopRatedMovies() }
            val onTheAirSeries = async { useCase.getOnTheAirSeries() }
            val popularSeries = async { useCase.getPopularSeries() }
            val topRatedSeries = async { useCase.getTopRatedSeries() }
            val movieRequestsResult =
                awaitAll(
                    popularMovies,
                    nowPlayingMovies,
                    upcomingMovies,
                    topRatedMovies,
                    onTheAirSeries,
                    popularSeries,
                    topRatedSeries
                )

            _imagesMoviesUiState.value = BaseUiState.Success(setImageSliderData(movieRequestsResult[0]))

            _popularMoviesUiState.value = BaseUiState.Success(movieRequestsResult[0])
            _nowPlayingMoviesUiState.value = BaseUiState.Success(movieRequestsResult[1])
            _upcomingMoviesUiState.value = BaseUiState.Success(movieRequestsResult[2])
            _topRatedMoviesUiState.value = BaseUiState.Success(movieRequestsResult[3])
            _onTheAirSeriesUiState.value = BaseUiState.Success(movieRequestsResult[4])
            _popularSeriesUiState.value = BaseUiState.Success(movieRequestsResult[5])
            _topRatedSeriesUiState.value = BaseUiState.Success(movieRequestsResult[6])
        }
    }

    private fun setImageSliderData(moviesList: MovieResponse?): ImagesMoviesModel {
        val imagesList = mutableListOf<String?>()
        for (i in 0 until 10) {
            imagesList.add(moviesList?.results?.get(i)?.backdropPath)
        }
        return ImagesMoviesModel(imagesList)
    }
}