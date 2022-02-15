package com.example.movies.network

import com.example.movies.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key")
        apiKey: String = "api_key",
        @Query("page")
        pageNumber: Int =1
    ): Response<MovieResponse>

    @GET("search/movie")
    fun SearchMovie(
        @Query("api_key")
        apiKey: String = "api_key",
        @Query("page")
        pageNumber: Int =1,
        @Query("query")
        q: String
    ): Response<MovieResponse>
}