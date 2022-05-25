package com.example.movies.data_remote.api

import com.example.movies.core.Constants.Companion.MOVIE_DETAILS
import com.example.movies.core.Constants.Companion.POPULAR
import com.example.movies.data_remote.dto.MovieDetailsDto
import com.example.movies.data_remote.dto.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApi {

    @GET(POPULAR)
    suspend fun getPopularMovies(): MovieResponse

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(@Path("movie_id") movieId: Int): MovieDetailsDto
}