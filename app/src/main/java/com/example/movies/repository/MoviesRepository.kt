package com.example.movies.repository

import com.example.movies.db.MovieDatabase
import com.example.movies.network.RetrofitInstance

class MoviesRepository (
    val db: MovieDatabase
        ){
    suspend fun getPopularMovies()=
        RetrofitInstance.api.getPopularMovies()
}



//hasn,t impelemented the page parameter in the api service