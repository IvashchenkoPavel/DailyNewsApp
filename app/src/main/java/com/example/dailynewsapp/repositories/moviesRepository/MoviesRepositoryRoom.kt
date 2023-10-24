package com.example.dailynewsapp.repositories.moviesRepository

import android.content.Context
import com.example.dailynewsapp.room.movieRoom.MovieTableModel
import com.example.dailynewsapp.room.movieRoom.MoviesDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesRepositoryRoom {
    companion object {
        private var moviesDB: MoviesDB? = null
        private var movieList: List<MovieTableModel>? = null

        private fun initializeDB(context: Context): MoviesDB {
            return MoviesDB.getDbMovies(context)
        }

        suspend fun insertMoviesData(context: Context, moviesList: List<MovieTableModel>) {
            CoroutineScope(Dispatchers.IO).launch {
                moviesDB = initializeDB(context)
                val ids = moviesDB?.movieDao()?.insertMoviesData(moviesList)
            }
        }

        suspend fun getMoviesData(context: Context): List<MovieTableModel>? {
            moviesDB = initializeDB(context)
            movieList = moviesDB?.movieDao()?.getMoviesData()
            return movieList
        }

        suspend fun deleteMoviesData(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                moviesDB = initializeDB(context)
                moviesDB?.movieDao()?.delete()
            }

        }

    }
}