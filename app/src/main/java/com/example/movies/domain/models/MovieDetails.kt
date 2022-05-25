package com.example.movies.domain.models

data class MovieDetails(
    val id: Int = 0,
    val overview: String = "",
    val backdropPath: String = "",
    val releaseDate: String = "",
    val tagline: String = "",
    val title: String = "",
    val rating: Double
)
