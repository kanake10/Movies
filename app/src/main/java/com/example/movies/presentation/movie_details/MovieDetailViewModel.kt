package com.example.movies.presentation.movie_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.core.Resource
import com.example.movies.domain.models.MovieDetails
import com.example.movies.domain.usecases.GetMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieDetailsUseCase: GetMovieDetailsUseCase
) : ViewModel() {

    private val _movieDetails = MutableLiveData<Resource<MovieDetails>>()
    val movieDetails: LiveData<Resource<MovieDetails>>
        get() = _movieDetails

    fun getMovieDetails(movieId: Int) {
        getMovieDetailsUseCase(movieId).onEach {
            _movieDetails.postValue(it)
        }.launchIn(viewModelScope)
    }
}