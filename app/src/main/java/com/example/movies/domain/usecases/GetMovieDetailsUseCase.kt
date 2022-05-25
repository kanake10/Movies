package com.example.movies.domain.usecases

import android.content.Context
import com.example.movies.R
import com.example.movies.core.Resource
import com.example.movies.data_remote.dto.toMovieDetails
import com.example.movies.domain.models.MovieDetails
import com.example.movies.domain.repository.MovieRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val repository: MovieRepository
) {
    operator fun invoke(movieId: Int): Flow<Resource<MovieDetails>> = flow {
        try {
            emit(Resource.Loading())
            val movieDetails = repository.getMovieDetails(movieId).toMovieDetails()
            emit(Resource.Success(movieDetails))
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    e.localizedMessage ?: appContext.getString(R.string.http_error_msg)
                )
            )
        } catch (e: IOException) {
            emit(Resource.Error(appContext.getString(R.string.io_error_msg)))
        }
    }
}