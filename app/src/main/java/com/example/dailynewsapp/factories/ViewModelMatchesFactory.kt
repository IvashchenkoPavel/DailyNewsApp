package com.example.dailynewsapp.factories

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dailynewsapp.repositories.matchesRepository.MatchesRepositoryRetrofit
import com.example.dailynewsapp.viewmodels.MatchesViewModel

class ViewModelMatchesFactory constructor(
    private val repository: MatchesRepositoryRetrofit, private val context: Context?): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MatchesViewModel::class.java)){
            MatchesViewModel(this.repository, this.context) as T
        } else {
            throw IllegalArgumentException("ViewModel not found")
        }
    }
}