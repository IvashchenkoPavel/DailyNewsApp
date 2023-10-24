package com.example.dailynewsapp.viewmodels

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dailynewsapp.models.AwayTeam
import com.example.dailynewsapp.models.Caption
import com.example.dailynewsapp.models.HomeTeam
import com.example.dailynewsapp.models.Image
import com.example.dailynewsapp.models.MatchesObject
import com.example.dailynewsapp.models.Movie
import com.example.dailynewsapp.models.Scores
import com.example.dailynewsapp.models.Teams
import com.example.dailynewsapp.repositories.matchesRepository.MatchesRepositoryRetrofit
import com.example.dailynewsapp.repositories.matchesRepository.MatchesRepositoryRoom
import com.example.dailynewsapp.repositories.moviesRepository.MoviesRepositoryRoom
import com.example.dailynewsapp.room.matchRoom.MatchesTableModel
import com.example.dailynewsapp.room.movieRoom.MovieTableModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MatchesViewModel(
    private val matchesRepository: MatchesRepositoryRetrofit,
    private val context: Context?
) :
    ViewModel() {
    private val errorMessage = MutableLiveData<String>()
    val matches = MutableLiveData<List<Scores>>()
    private var job: Job? = null
    private val loading = MutableLiveData<Boolean>()
    val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    fun getAllMatches() {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val listMatchesTableModel = context?.let { MatchesRepositoryRoom.getMatchesData(it) }
            if (listMatchesTableModel != null && listMatchesTableModel.isNotEmpty()) {
                val toScoreList = mutableListOf<Scores>()
                for (matchModel in listMatchesTableModel) {
                    val score = Scores(
                        Teams(
                            AwayTeam(
                                matchModel.firstTeamName,
                                matchModel.logoFirstTeam,
                                matchModel.firstTeamCity
                            ),
                            HomeTeam(
                                matchModel.secondTeamName,
                                matchModel.logoSecondTeam,
                                matchModel.secondTeamCity
                            )
                        )
                    )
                    toScoreList.add(score)
                }
                matches.postValue(toScoreList)
            } else {
                val response = matchesRepository.getAllMovies()
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        val matchesObject: MatchesObject? = response.body()
                        val scoresList = mutableListOf<Scores>()
                        for (score in matchesObject?.scores!!) {
                            scoresList.add(score)
                        }
                        matches.postValue(scoresList)
                        val matchesListModel = mutableListOf<MatchesTableModel>()
                        for (score in scoresList) {
                            val matchesModel = MatchesTableModel(
                                score.teams.awayTeam.logo,
                                score.teams.awayTeam.displayName,
                                score.teams.awayTeam.location,
                                score.teams.homeTeam.logo,
                                score.teams.homeTeam.displayName,
                                score.teams.homeTeam.location
                            )
                            matchesListModel.add(matchesModel)
                        }
                        context?.let { MatchesRepositoryRoom.deleteMatchesData(it) }
                        context?.let {
                            MatchesRepositoryRoom.insertMatchesData(
                                it,
                                matchesListModel
                            )
                        }
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