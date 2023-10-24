package com.example.dailynewsapp.room.matchRoom

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface MatchesDAO {

@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insertMatchesData(movieTableModelList: List<MatchesTableModel>): List<Long>

@Query("SELECT * FROM matches")
suspend fun getMatchesData(): List<MatchesTableModel>

@Query("DELETE FROM matches")
fun delete()

}