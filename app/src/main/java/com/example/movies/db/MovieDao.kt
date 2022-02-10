package com.example.movies.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.movies.model.Result

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(result: Result): Long

    @Query("SELECT * FROM movies")
    fun getAllArticles(): LiveData<List<Result>>

    @Delete
    suspend fun deleteArticle(article: Result)
}