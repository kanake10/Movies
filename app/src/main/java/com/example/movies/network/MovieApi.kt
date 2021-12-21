package com.example.movies.network

import com.example.movies.model.GetMoviesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "api_key",
        @Query("page") page: Int
    ): Response<GetMoviesResponse>
}