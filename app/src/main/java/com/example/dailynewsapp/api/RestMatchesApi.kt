package com.example.dailynewsapp.api

import com.example.dailynewsapp.models.MatchesObject
import com.example.dailynewsapp.models.MovieObject
import retrofit2.Response
import retrofit2.http.GET

interface RestMatchesApi {
    @GET("nfl/events")
    suspend fun getUpcomingMatches(): Response<MatchesObject>
}