package com.example.dailynewsapp.repositories.moviesRepository

import com.example.dailynewsapp.api.MoviesService

class MoviesRepositoryRetrofit(private val movieService: MoviesService) {

    suspend fun getAllMovies() = movieService.restMoviesApi.getUpcomingMovies()

}