package com.example.dailynewsapp.models

data class MovieObject (
    val results: List<Movie>
)
data class Movie(
val primaryImage: Image
)
data class Image(
    val url: String,
    val caption: Caption
)
data class Caption(
    val plainText: String
)