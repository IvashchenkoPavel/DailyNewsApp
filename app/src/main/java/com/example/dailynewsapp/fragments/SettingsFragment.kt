package com.example.dailynewsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.dailynewsapp.adapter.MoviesAdapter
import com.example.dailynewsapp.databinding.SettingsFragmentBinding
import com.example.dailynewsapp.viewmodels.MoviesViewModel

class SettingsFragment : Fragment() {
    private lateinit var binding: SettingsFragmentBinding
    private lateinit var movieViewModel: MoviesViewModel
    private var movieAdapter: MoviesAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SettingsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)

    }
}