package com.example.movies.network

import com.example.movies.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key")
        apiKey: String = "api_key"
    ): Response<MovieResponse>

    @GET("search/movie")
    suspend fun searchForMovies(
        @Query("query")
        searchQuery: String,
        @Query("api_Key")
        apiKey: String = "api_key"
    ): Response<MovieResponse>
}