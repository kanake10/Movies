package com.example.movies.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movies.repository.MoviesRepository

class MoviesViewModelProviderFactory (
    val moviesRepository: MoviesRepository
        ) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MoviesViewModel(moviesRepository) as T
        }
}
