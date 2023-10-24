package com.example.dailynewsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dailynewsapp.adapter.MoviesAdapter
import com.example.dailynewsapp.api.MoviesService
import com.example.dailynewsapp.databinding.MoviesFragmentBinding
import com.example.dailynewsapp.factories.ViewModelMoviesFactory
import com.example.dailynewsapp.repositories.moviesRepository.MoviesRepositoryRetrofit
import com.example.dailynewsapp.util.SpacingItemDecorator
import com.example.dailynewsapp.viewmodels.MoviesViewModel

class MoviesFragment : Fragment() {
    private lateinit var binding: MoviesFragmentBinding
    private lateinit var movieViewModel: MoviesViewModel
    private var movieAdapter: MoviesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MoviesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

        val movieRepository = MoviesRepositoryRetrofit(MoviesService())
        movieViewModel = ViewModelProvider(
            this,
            ViewModelMoviesFactory(movieRepository, context)
        )[MoviesViewModel::class.java]
        movieViewModel.getAllMovies()
        binding.rvMovies.layoutManager = LinearLayoutManager(activity)
        binding.rvMovies.addItemDecoration(SpacingItemDecorator(20))
        movieViewModel.movie.observe(viewLifecycleOwner) {
            movieAdapter = MoviesAdapter(it, context)
            binding.rvMovies.adapter = movieAdapter
        }
    }
}