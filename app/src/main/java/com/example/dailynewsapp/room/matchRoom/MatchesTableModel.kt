package com.example.dailynewsapp.room.matchRoom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "matches")
data class MatchesTableModel(

    @ColumnInfo(name = "logoFirstTeam")
    var logoFirstTeam: String,

    @ColumnInfo(name = "firstTeamName")
    var firstTeamName: String,

    @ColumnInfo(name = "firstTeamCity")
    var firstTeamCity: String,

    @ColumnInfo(name = "logoSecondTeam")
    var logoSecondTeam: String,

    @ColumnInfo(name = "secondTeamName")
    var secondTeamName: String,

    @ColumnInfo(name = "secondTeamCity")
    var secondTeamCity: String
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_id")
    var id: Int? = null
}