package com.example.dailynewsapp.adapter

import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.dailynewsapp.R
import com.example.dailynewsapp.fragments.FIRST_TEAM_CITY
import com.example.dailynewsapp.fragments.FIRST_TEAM_LOGO
import com.example.dailynewsapp.fragments.FIRST_TEAM_NAME
import com.example.dailynewsapp.fragments.MOVIE_CAPTION
import com.example.dailynewsapp.fragments.MOVIE_PICTURE
import com.example.dailynewsapp.fragments.SECOND_TEAM_CITY
import com.example.dailynewsapp.fragments.SECOND_TEAM_LOGO
import com.example.dailynewsapp.fragments.SECOND_TEAM_NAME
import com.example.dailynewsapp.models.Scores


class ScoresAdapter(
    private val scoresList: List<Scores>, private val context: Context?
) : RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.matches_rv_item, parent, false)
        return ScoresViewHolder(itemView, context, scoresList)
    }

    override fun getItemCount(): Int {
        return scoresList.size
    }

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) {
        val logoFirstTeam: String = scoresList[position].teams.awayTeam.logo
        val firstTeamName: String? = scoresList[position].teams.awayTeam.displayName
        val firstTeamCity: String? = scoresList[position].teams.awayTeam.location
        val logoSecondTeam: String = scoresList[position].teams.homeTeam.logo
        val secondTeamName: String? = scoresList[position].teams.homeTeam.displayName
        val secondTeamCity: String? = scoresList[position].teams.homeTeam.location

        context?.let {
            Glide.with(it)
                .load(logoFirstTeam)
                .into(holder.firstTeamLogo)
        }
        holder.firstTeamName.text = firstTeamName
        holder.firstTeamName.movementMethod = ScrollingMovementMethod()
        holder.firstTeamCity.text = firstTeamCity

        context?.let {
            Glide.with(it)
                .load(logoSecondTeam)
                .into(holder.secondTeamLogo)
        }
        holder.secondTeamName.text = secondTeamName
        holder.secondTeamCity.text = secondTeamCity
    }

    class ScoresViewHolder(itemView: View, context: Context?, scoresList: List<Scores>) : RecyclerView.ViewHolder(itemView) {

        val firstTeamName: TextView
        val firstTeamCity: TextView
        val firstTeamLogo: ImageView
        val secondTeamName: TextView
        val secondTeamCity: TextView
        val secondTeamLogo: ImageView
        var navController: NavController? = null
        init {
            firstTeamName = itemView.findViewById(R.id.tv_first_team_name)
            firstTeamCity = itemView.findViewById(R.id.first_team_city)
            firstTeamLogo = itemView.findViewById(R.id.logo_first_team)
            secondTeamName = itemView.findViewById(R.id.tv_second_team_name)
            secondTeamCity = itemView.findViewById(R.id.second_team_city)
            secondTeamLogo = itemView.findViewById(R.id.logo_second_team)

            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(FIRST_TEAM_LOGO,  scoresList[this.adapterPosition].teams.awayTeam.logo)
                bundle.putString(SECOND_TEAM_LOGO, scoresList[this.adapterPosition].teams.homeTeam.logo)
                bundle.putString(FIRST_TEAM_NAME, scoresList[this.adapterPosition].teams.awayTeam.displayName)
                bundle.putString(SECOND_TEAM_NAME, scoresList[this.adapterPosition].teams.homeTeam.displayName)
                bundle.putString(FIRST_TEAM_CITY, scoresList[this.adapterPosition].teams.awayTeam.location)
                bundle.putString(SECOND_TEAM_CITY, scoresList[this.adapterPosition].teams.homeTeam.location)
                navController = Navigation.findNavController(itemView)
                navController?.navigate(R.id.matches_detail, bundle)
            }
        }
    }
}