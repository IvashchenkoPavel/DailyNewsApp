package com.example.dailynewsapp.repositories.matchesRepository

import android.content.Context
import com.example.dailynewsapp.room.matchRoom.MatchesDB
import com.example.dailynewsapp.room.matchRoom.MatchesTableModel
import com.example.dailynewsapp.room.movieRoom.MovieTableModel
import com.example.dailynewsapp.room.movieRoom.MoviesDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchesRepositoryRoom {
    companion object {
        private var matchesDB: MatchesDB? = null
        private var matchesList: List<MatchesTableModel>? = null

        private fun initializeDB(context: Context): MatchesDB {
            return MatchesDB.getDbMatches(context)
        }

        suspend fun insertMatchesData(context: Context, matchesList: List<MatchesTableModel>) {
            CoroutineScope(Dispatchers.IO).launch {
                matchesDB = initializeDB(context)
                val ids = matchesDB?.matchesDao()?.insertMatchesData(matchesList)
            }
        }

        suspend fun getMatchesData(context: Context): List<MatchesTableModel>? {
            matchesDB = initializeDB(context)
            matchesList = matchesDB?.matchesDao()?.getMatchesData()
            return matchesList
        }

        suspend fun deleteMatchesData(context: Context) {
            CoroutineScope(Dispatchers.IO).launch {
                matchesDB = initializeDB(context)
                matchesDB?.matchesDao()?.delete()
            }

        }

    }
}