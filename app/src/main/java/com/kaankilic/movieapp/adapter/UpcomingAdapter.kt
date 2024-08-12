package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.View.MovieListFragmentDirections
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.model.MovieResult

class UpcomingAdapter(val mContext : Context, var movieList : ArrayList<MovieResult>) : RecyclerView.Adapter<UpcomingAdapter.UpcomingViewHolder>() {

    class UpcomingViewHolder(val binding : MovieRecyclerRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val binding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UpcomingViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val movie = movieList.get(position)

        holder.binding.yLdZText.text=movieList[position].voteAverage.toString()
        holder.binding.titleText.text= movieList[position].originalTitle

        val url = "https://image.tmdb.org/t/p/w500${movieList[position].backdropPath}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterView)

        holder.itemView.setOnClickListener {
            val action= MovieListFragmentDirections.actionMovieListFragmentToMovieDetailFragment(movies = movie)
            Navigation.findNavController(it).navigate(action)

        }


    }
}