package com.example.movies.domain.repository

import com.example.movies.data_remote.dto.MovieDetailsDto
import com.example.movies.data_remote.dto.MovieResponse

interface MovieRepository {
    suspend fun getPopularMovies(): MovieResponse
    suspend fun getMovieDetails(movieId: Int): MovieDetailsDto
}