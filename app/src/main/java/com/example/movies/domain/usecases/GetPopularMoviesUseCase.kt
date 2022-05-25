package com.example.movies.domain.usecases

import android.content.Context
import com.example.movies.R
import com.example.movies.core.Resource
import com.example.movies.data_remote.dto.toMovie
import com.example.movies.domain.models.Movie
import com.example.movies.domain.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularMoviesUseCase @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val repository: MovieRepository
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        try {
            emit(Resource.Loading())
            val list = repository.getPopularMovies().movieList.map { it.toMovie() }
            emit(Resource.Success(list))
        } catch(e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: appContext.getString(R.string.http_error_msg)))
        } catch(e: IOException) {
            emit(Resource.Error(appContext.getString(R.string.io_error_msg)))
        }
    }
}