package br.com.study.tmdb_prime_video.home.data.network

import br.com.study.tmdb_prime_video.home.data.model.MoviesResponse
import br.com.study.tmdb_prime_video.home.data.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeApi {

    @GET("$POPULAR_MOVIES$")
    suspend fun getMovies(): Response<MoviesResponse>

    @GET("$SEARCH_MOVIE$/{$SEARCH_TEXT}")
    suspend fun getSearchMovie(@Path(SEARCH_TEXT) text: String?): Response<SearchResponse>

    companion object {

        private const val POPULAR_MOVIES = "mostpopularmovies/"
        private const val SEARCH_MOVIE = "searchmovie/"
        private const val SEARCH_TEXT = "text"
    }
}