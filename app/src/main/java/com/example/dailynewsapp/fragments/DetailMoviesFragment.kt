package com.example.dailynewsapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.dailynewsapp.databinding.DetailMoviesFragmentBinding

const val MOVIE_PICTURE = "param1"
const val MOVIE_CAPTION = "param2"

class DetailMoviesFragment : Fragment() {
    private lateinit var binding: DetailMoviesFragmentBinding
    private var movie_pic: String? = null
    private var movie_caption: String? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DetailMoviesFragmentBinding.inflate(inflater, container, false)
        arguments?.let {
            movie_pic = it.getString(MOVIE_PICTURE)
            movie_caption = it.getString(MOVIE_CAPTION)
        }
        return binding.root
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        context?.let {
            Glide.with(it)
                .load(movie_pic)
                .into(binding.movieImage)
        }
        binding.caption.text = movie_caption
    }

    companion object {
        private const val TAG = "DetailMoviesFragment"

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMoviesFragment().apply {
                arguments = Bundle().apply {
                    putString(MOVIE_PICTURE, param1)
                    putString(MOVIE_CAPTION, param2)
                }
            }
    }
}