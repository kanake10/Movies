package com.example.movies.data_remote.repository

import com.example.movies.data_remote.api.MovieApi
import com.example.movies.data_remote.dto.MovieDetailsDto
import com.example.movies.data_remote.dto.MovieResponse
import com.example.movies.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(private val movieApi: MovieApi) : MovieRepository {

    override suspend fun getPopularMovies(): MovieResponse {
        return movieApi.getPopularMovies()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailsDto {
        return movieApi.getMovieDetails(movieId)
    }
}