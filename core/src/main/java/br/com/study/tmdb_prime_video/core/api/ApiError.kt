package br.com.study.tmdb_prime_video.core.api

data class APIError(val message: String) {
    constructor() : this("")
}