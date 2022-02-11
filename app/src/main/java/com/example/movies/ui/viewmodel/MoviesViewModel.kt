package com.example.movies.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movies.core.Resource
import com.example.movies.model.MovieResponse
import com.example.movies.repository.MoviesRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class MoviesViewModel(
    val moviesRepository: MoviesRepository
) : ViewModel() {

    val popularMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()
    //var breakingNewsPage = 1

    init {
        getPopularMovies()
    }

    fun getPopularMovies() = viewModelScope.launch {
        popularMovies.postValue(Resource.Loading())
        val response = moviesRepository.getPopularMovies( )
        popularMovies.postValue(handleBreakingNewsResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<MovieResponse>) : Resource<MovieResponse> {
        if(response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}