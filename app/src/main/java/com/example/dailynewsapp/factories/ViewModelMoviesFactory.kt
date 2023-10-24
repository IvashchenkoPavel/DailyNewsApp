package com.example.dailynewsapp.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailynewsapp.repositories.moviesRepository.MoviesRepositoryRetrofit
import com.example.dailynewsapp.viewmodels.MoviesViewModel

class ViewModelMoviesFactory constructor(
    private val repository: MoviesRepositoryRetrofit, private val context: Context?): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MoviesViewModel::class.java)){
            MoviesViewModel(this.repository, this.context) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}