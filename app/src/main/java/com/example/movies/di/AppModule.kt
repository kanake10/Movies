package com.example.movies.di

import com.example.movies.core.Constants
import com.example.movies.data.remote.MovieApi
import com.example.movies.data.remote.repository.MovieRepositoryImpl
import com.example.movies.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMovieApi(): MovieApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MovieApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMovieRepository(api: MovieApi): MovieRepository {
        return MovieRepositoryImpl(api)
    }
}