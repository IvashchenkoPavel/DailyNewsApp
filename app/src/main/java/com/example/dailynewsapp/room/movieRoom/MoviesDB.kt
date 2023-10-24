package com.example.dailynewsapp.room.movieRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MovieTableModel::class], version = 1, exportSchema = false)
abstract class MoviesDB : RoomDatabase() {

    abstract fun movieDao(): MoviesDAO

    companion object {

        @Volatile
        private var INSTANCE: MoviesDB? = null

        fun getDbMovies(context: Context): MoviesDB {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MoviesDB::class.java, "MOVIES_DB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }
}