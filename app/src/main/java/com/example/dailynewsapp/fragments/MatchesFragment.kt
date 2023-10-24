package com.example.dailynewsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynewsapp.adapter.ScoresAdapter
import com.example.dailynewsapp.api.MatchesService
import com.example.dailynewsapp.databinding.MatchesFragmentBinding
import com.example.dailynewsapp.factories.ViewModelMatchesFactory
import com.example.dailynewsapp.repositories.matchesRepository.MatchesRepositoryRetrofit
import com.example.dailynewsapp.util.SpacingItemDecorator
import com.example.dailynewsapp.viewmodels.MatchesViewModel

class MatchesFragment : Fragment() {
    private lateinit var binding: MatchesFragmentBinding
    private lateinit var matchesViewModel: MatchesViewModel
    private var scoresAdapter: ScoresAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MatchesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        val matchesRepository = MatchesRepositoryRetrofit(MatchesService())
        matchesViewModel = ViewModelProvider(
            this,
            ViewModelMatchesFactory(matchesRepository, context)
        )[MatchesViewModel::class.java]
        matchesViewModel.getAllMatches()
        binding.rvMatches.layoutManager = LinearLayoutManager(activity)
        binding.rvMatches.addItemDecoration(SpacingItemDecorator(12))
        matchesViewModel.matches.observe(viewLifecycleOwner) {
            scoresAdapter = ScoresAdapter(it, context)
            binding.rvMatches.adapter = scoresAdapter
        }
    }
}