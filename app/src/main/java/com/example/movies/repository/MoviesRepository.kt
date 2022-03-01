package com.example.movies.repository

import com.example.movies.db.MovieDatabase
import com.example.movies.model.Result
import com.example.movies.network.RetrofitInstance

class MoviesRepository (
    val db: MovieDatabase
        ){
    suspend fun getPopularMovies()=
        RetrofitInstance.api.getPopularMovies()

    suspend fun searchMovies(searchQuery: String) =
        RetrofitInstance.api.searchForMovies(searchQuery)


    suspend fun upsert(result: Result) = db.getMovieDao().upsert(result)

    fun getSavedMovies() = db.getMovieDao().getAllMovies()

    suspend fun deleteArticle(result: Result) = db.getMovieDao().deleteMovies(result)


}



