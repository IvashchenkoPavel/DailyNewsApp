package com.example.dailynewsapp.models

data class MatchesObject (
    val scores: List<Scores>
)
data class Scores(
    val teams: Teams
)
data class Teams(
    val awayTeam: AwayTeam,
    val homeTeam: HomeTeam
)
data class AwayTeam(
    val displayName: String,
    val logo: String,
    val location: String
)
data class HomeTeam(
    val displayName: String,
    val logo: String,
    val location: String
)