package com.example.dailynewsapp.room.matchRoom

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [MatchesTableModel::class], version = 1, exportSchema = false)
abstract class MatchesDB : RoomDatabase() {

    abstract fun matchesDao(): MatchesDAO

    companion object {

        @Volatile
        private var INSTANCE: MatchesDB? = null

        fun getDbMatches(context: Context): MatchesDB {
            if (INSTANCE != null) return INSTANCE!!

            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, MatchesDB::class.java, "MATCHES_DB")
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }

    }
}