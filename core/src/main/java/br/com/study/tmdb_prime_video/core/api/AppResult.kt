package br.com.study.tmdb_prime_video.core.api

sealed class AppResult<out T> {

    data class Success<out T>(val value: T) : AppResult<T>()
    data class Error(val exception: Exception) : AppResult<Nothing>()

    fun <M> map(mapper: (originalData: T) -> M): AppResult<M> = when (this) {
        is Success -> Success(mapper(value))
        is Error -> Error(exception = exception)
    }
}

inline fun <T> AppResult<T>.onFailure(action: (exception: Exception) -> Unit): AppResult<T> {
    if (this is AppResult.Error) {
        action(exception)
    }
    return this
}

inline fun <T> AppResult<T>.onSuccess(action: (value: T) -> Unit): AppResult<T> {
    if (this is AppResult.Success<T>) {
        action(value)
    }
    return this
}