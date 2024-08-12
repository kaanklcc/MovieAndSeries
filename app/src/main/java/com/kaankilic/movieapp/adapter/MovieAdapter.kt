package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.View.MovieListFragmentDirections
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.databinding.PopularRecyclerViewBinding
import com.kaankilic.movieapp.model.MovieResult

class MovieAdapter(var mContext : Context, var movieListesi : ArrayList<MovieResult>) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    class MovieViewHolder(val binding : PopularRecyclerViewBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = PopularRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieListesi.size
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieListesi.get(position)
       holder.binding.titleText.text= movieListesi[position].originalTitle
        holder.binding.yLdZText.text= movieListesi[position].voteAverage.toString()


        val url = "https://image.tmdb.org/t/p/w500${movieListesi[position].backdropPath}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterImageView)

        holder.itemView.setOnClickListener {
            val action= MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movies = movie)
            Navigation.findNavController(it).navigate(action)

        }




    }




}