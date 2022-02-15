package com.example.movies.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movies.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result: Result)

    @Query("SELECT * FROM movies")
    fun getAllMovies(): LiveData<List<Result>>

    @Delete
    suspend fun deleteMovies(article: Result)
}