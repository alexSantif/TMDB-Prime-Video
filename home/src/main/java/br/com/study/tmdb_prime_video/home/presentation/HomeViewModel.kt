package br.com.study.tmdb_prime_video.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study.tmdb_prime_video.core.base.BaseUiState
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

    fun getHomeMovies() {
        viewModelScope.launch {
            val popularMovies = async { useCase.getPopularMovies() }
            val nowPlayingMovies = async { useCase.getNowPlayingMovies() }
            val upcomingMovies = async { useCase.getUpcomingMovies() }
            val topRatedMovies = async { useCase.getTopRatedMovies() }
            val movieRequestsResult =
                awaitAll(popularMovies, nowPlayingMovies, upcomingMovies, topRatedMovies)
            _popularMoviesUiState.value = BaseUiState.Success(movieRequestsResult[0])
            _nowPlayingMoviesUiState.value = BaseUiState.Success(movieRequestsResult[1])
            _upcomingMoviesUiState.value = BaseUiState.Success(movieRequestsResult[2])
            _topRatedMoviesUiState.value = BaseUiState.Success(movieRequestsResult[3])
        }
    }
}