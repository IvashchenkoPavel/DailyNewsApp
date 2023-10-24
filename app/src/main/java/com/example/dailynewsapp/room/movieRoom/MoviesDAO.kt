package com.example.dailynewsapp.room.movieRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MoviesDAO {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertMoviesData(movieTableModelList: List<MovieTableModel>): List<Long>

@Query("SELECT * FROM movies")
suspend fun getMoviesData(): List<MovieTableModel>

@Query("DELETE FROM movies")
fun delete()

}