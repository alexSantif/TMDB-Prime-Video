package br.com.study.tmdb_prime_video.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import br.com.study.tmdb_prime_video.core.base.BaseUiState
import br.com.study.tmdb_prime_video.core.mock.getNowPlayingMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getOnTheAirSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getPopularMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getPopularSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getTopRatedMoviesMock
import br.com.study.tmdb_prime_video.core.mock.getTopRatedSeriesMock
import br.com.study.tmdb_prime_video.core.mock.getUpcomingMoviesMock
import br.com.study.tmdb_prime_video.home.data.model.MovieResponse
import br.com.study.tmdb_prime_video.home.domain.HomeUseCase
import br.com.study.tmdb_prime_video.home.presentation.HomeViewModel
import com.google.gson.Gson
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private val useCase: HomeUseCase = mockk()

    private val dispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun test_updateHomeWithFetchedData() = runTest {
        val popularMoviesData = Gson().fromJson(getPopularMoviesMock(), MovieResponse::class.java)
        val nowPlayingMoviesData = Gson().fromJson(getNowPlayingMoviesMock(), MovieResponse::class.java)
        val upcomingMoviesData = Gson().fromJson(getUpcomingMoviesMock(), MovieResponse::class.java)
        val topRatedMoviesData = Gson().fromJson(getTopRatedMoviesMock(), MovieResponse::class.java)
        val onTheAirSeriesData = Gson().fromJson(getOnTheAirSeriesMock(), MovieResponse::class.java)
        val popularSeriesData = Gson().fromJson(getPopularSeriesMock(), MovieResponse::class.java)
        val topRatedSeriesData = Gson().fromJson(getTopRatedSeriesMock(), MovieResponse::class.java)

        coEvery { useCase.getPopularMovies() } returns Gson().fromJson(getPopularMoviesMock(), MovieResponse::class.java)
        coEvery { useCase.getNowPlayingMovies() } returns Gson().fromJson(getNowPlayingMoviesMock(), MovieResponse::class.java)
        coEvery { useCase.getUpcomingMovies() } returns Gson().fromJson(getUpcomingMoviesMock(), MovieResponse::class.java)
        coEvery { useCase.getTopRatedMovies() } returns Gson().fromJson(getTopRatedMoviesMock(), MovieResponse::class.java)
        coEvery { useCase.getOnTheAirSeries() } returns Gson().fromJson(getOnTheAirSeriesMock(), MovieResponse::class.java)
        coEvery { useCase.getPopularSeries() } returns Gson().fromJson(getPopularSeriesMock(), MovieResponse::class.java)
        coEvery { useCase.getTopRatedSeries() } returns Gson().fromJson(getTopRatedSeriesMock(), MovieResponse::class.java)

        viewModel.getHomeMovies()

        advanceUntilIdle()

        assertEquals(BaseUiState.Success(popularMoviesData), viewModel.popularMoviesUiState.value)
        assertEquals(BaseUiState.Success(nowPlayingMoviesData), viewModel.nowPlayingMoviesUiState.value)
        assertEquals(BaseUiState.Success(upcomingMoviesData), viewModel.upcomingMoviesUiState.value)
        assertEquals(BaseUiState.Success(topRatedMoviesData), viewModel.topRatedMoviesUiState.value)
        assertEquals(BaseUiState.Success(onTheAirSeriesData), viewModel.onTheAirSeriesUiState.value)
        assertEquals(BaseUiState.Success(popularSeriesData), viewModel.popularSeriesUiState.value)
        assertEquals(BaseUiState.Success(topRatedSeriesData), viewModel.topRatedSeriesUiState.value)
    }
}