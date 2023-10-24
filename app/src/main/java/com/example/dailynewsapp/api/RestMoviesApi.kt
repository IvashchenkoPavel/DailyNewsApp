package com.example.dailynewsapp.api

import com.example.dailynewsapp.models.MovieObject
import retrofit2.Response
import retrofit2.http.GET

interface RestMoviesApi {
    @GET("upcoming")
    suspend fun getUpcomingMovies(): Response<MovieObject>
}