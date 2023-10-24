package com.example.dailynewsapp.repositories.matchesRepository

import com.example.dailynewsapp.api.MatchesService
import com.example.dailynewsapp.api.MoviesService

class MatchesRepositoryRetrofit(private val matchesService: MatchesService) {

    suspend fun getAllMovies() = matchesService.restMatchesApi.getUpcomingMatches()

}