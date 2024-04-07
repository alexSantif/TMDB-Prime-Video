package br.com.study.tmdb_prime_video.home.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.study.tmdb_prime_video.core.model.ResourceData
import br.com.study.tmdb_prime_video.home.domain.HomeUseCase
import kotlinx.coroutines.launch

class HomeViewModel(private val useCase: HomeUseCase) : ViewModel() {

    val movies = MutableLiveData<ResourceData?>()

    val searchMovie = MutableLiveData<ResourceData?>()

    fun getMovies() {
        viewModelScope.launch {
            val moviesResult = useCase.getMovies()
            moviesResult?.data?.let {
                val resourceData = ResourceData(data = it)
                movies.value = resourceData
            } ?: let {
                moviesResult?.error?.let { error ->
                    val resourceData = ResourceData(error = error)
                    movies.value = resourceData
                }
            }
        }
    }

    fun getSearchMovie(text: String?) {
        viewModelScope.launch {
            val moviesResult = useCase.getSearchMovie(text)
            moviesResult?.data?.let {
                val resourceData = ResourceData(data = it)
                searchMovie.value = resourceData
            } ?: let {
                moviesResult?.error?.let { error ->
                    val resourceData = ResourceData(error = error)
                    searchMovie.value = resourceData
                }
            }
        }
    }

    fun clearMovieSearchResult() {
        searchMovie.value = null
    }
}