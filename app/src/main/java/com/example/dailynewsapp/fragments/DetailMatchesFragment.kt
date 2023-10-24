package com.example.dailynewsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dailynewsapp.databinding.DetailMatchesFragmentBinding

const val FIRST_TEAM_LOGO: String = "firstTeamLogo"
const val SECOND_TEAM_LOGO: String = "secondTeamLogo"
const val SECOND_TEAM_NAME: String = "secondTeamName"
const val FIRST_TEAM_NAME: String = "firstTeamName"
const val FIRST_TEAM_CITY: String = "firstTeamCity"
const val SECOND_TEAM_CITY: String = "secondTeamCity"

class DetailMatchesFragment : Fragment() {
    private lateinit var binding: DetailMatchesFragmentBinding
    private var logoFirstTeam: String? = null
    private var logoSecondTeam: String? = null
    private var firstTeamName: String? = null
    private var secondTeamName: String? = null
    private var firstTeamCity: String? = null
    private var secondTeamCity: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailMatchesFragmentBinding.inflate(inflater, container, false)
        arguments?.let {
            logoFirstTeam = it.getString(FIRST_TEAM_LOGO)
            logoSecondTeam = it.getString(SECOND_TEAM_LOGO)
            firstTeamName = it.getString(FIRST_TEAM_NAME)
            secondTeamName = it.getString(SECOND_TEAM_NAME)
            firstTeamCity = it.getString(FIRST_TEAM_CITY)
            secondTeamCity = it.getString(SECOND_TEAM_CITY)
        }
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load(logoFirstTeam)
                .into(binding.firstTeamLogo)
        }
        context?.let {
            Glide.with(it)
                .load(logoSecondTeam)
                .into(binding.secondTeamLogo)
        }
        binding.firstTeamName.text = firstTeamName
        binding.firstTeamCity.text = firstTeamCity
        binding.secondTeamName.text = secondTeamName
        binding.secondTeamCity.text = secondTeamCity
    }

    companion object {
        private const val TAG = "DetailMoviesFragment"

        @JvmStatic
        fun newInstance(
            logoFirstTeam: String,
            logoSecondTeam: String,
            firstTeamName: String,
            secondTeamName: String,
            firstTeamCity: String,
            secondTeamCity: String
        ) =
            DetailMatchesFragment().apply {
                arguments = Bundle().apply {
                    putString(FIRST_TEAM_LOGO, logoFirstTeam)
                    putString(SECOND_TEAM_LOGO, logoSecondTeam)
                    putString(FIRST_TEAM_NAME, firstTeamName)
                    putString(SECOND_TEAM_NAME, secondTeamName)
                    putString(FIRST_TEAM_CITY, firstTeamCity)
                    putString(SECOND_TEAM_CITY, secondTeamCity)

                }
            }
    }
}