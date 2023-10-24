package com.example.dailynewsapp.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynewsapp.models.Caption
import com.example.dailynewsapp.models.Image
import com.example.dailynewsapp.models.Movie
import com.example.dailynewsapp.models.MovieObject
import com.example.dailynewsapp.repositories.moviesRepository.MoviesRepositoryRetrofit
import com.example.dailynewsapp.repositories.moviesRepository.MoviesRepositoryRoom
import com.example.dailynewsapp.room.movieRoom.MovieTableModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MoviesViewModel(private val movieRepository: MoviesRepositoryRetrofit, private val context: Context?) :
    ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    val movie = MutableLiveData<List<Movie>>()
    private var job: Job? = null
    private val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMovies() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val listMovieTableModel = context?.let { MoviesRepositoryRoom.getMoviesData(it) }
            if (listMovieTableModel != null && listMovieTableModel.isNotEmpty()) {
                val toMovieList = mutableListOf<Movie>()
                for (movieModel in listMovieTableModel) {
                    val movie = Movie(Image(movieModel.imgUrl, Caption(movieModel.caption)))
                    toMovieList.add(movie)
                }
                movie.postValue(toMovieList)
            } else {
                val response = movieRepository.getAllMovies()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val movieListModel = mutableListOf<MovieTableModel>()
                        val movieObject: MovieObject? = response.body()
                        val movieList = mutableListOf<Movie>()
                        for (movie in movieObject?.results!!) {
                            if (movie.primaryImage != null) movieList.add(movie)
                        }
                        movie.postValue(movieList)
                        for (movieItem in movieList) {
                            val movieTableModel = MovieTableModel(
                                movieItem.primaryImage.url,
                                movieItem.primaryImage.caption.plainText
                            )
                            movieListModel.add(movieTableModel)
                        }
                        context?.let { MoviesRepositoryRoom.deleteMoviesData(it) }
                        context?.let { MoviesRepositoryRoom.insertMoviesData(it, movieListModel) }
                        loading.value = false
                    } else {
                        onError("Error : ${response.message()} ")
                    }
                }
            }
        }
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}