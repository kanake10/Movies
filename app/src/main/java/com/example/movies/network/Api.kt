package com.example.movies.network

import com.example.movies.model.GetMoviesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String = "c6b60cf1108ccfe7a3d10b96525dff17",
        @Query("page") page: Int
    ): Call<GetMoviesResponse>
}