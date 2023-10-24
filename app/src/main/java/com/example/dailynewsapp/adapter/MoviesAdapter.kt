package com.example.dailynewsapp.adapter

import android.content.Context
import android.os.Bundle
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
import com.example.dailynewsapp.fragments.DetailMoviesFragment
import com.example.dailynewsapp.fragments.MOVIE_CAPTION
import com.example.dailynewsapp.fragments.MOVIE_PICTURE
import com.example.dailynewsapp.models.Movie

class MoviesAdapter(
    private val moviesList: List<Movie>, private val context: Context?
) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_rv_item, parent, false)
        return MoviesViewHolder(itemView, context, moviesList)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val url: String = moviesList[position].primaryImage.url
        val caption: String? = moviesList[position].primaryImage.caption.plainText
        context?.let {
            Glide.with(it)
                .load(url)
                .into(holder.movieImage)
        }
        holder.movieCaption.text = caption
    }

    class MoviesViewHolder(itemView: View, val context: Context?, moviesList: List<Movie>) :
        RecyclerView.ViewHolder(itemView) {

        val movieCaption: TextView
        val movieImage: ImageView
        var navController: NavController? = null
        init {
            movieCaption = itemView.findViewById(R.id.movie_caption)
            movieImage = itemView.findViewById(R.id.logo_first_team)
            itemView.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(MOVIE_PICTURE, moviesList[this.adapterPosition].primaryImage.url)
                bundle.putString(MOVIE_CAPTION, moviesList[this.adapterPosition].primaryImage.caption.plainText)
                navController = Navigation.findNavController(itemView)
                navController?.navigate(R.id.movies_detail, bundle)

            }
        }
    }
}