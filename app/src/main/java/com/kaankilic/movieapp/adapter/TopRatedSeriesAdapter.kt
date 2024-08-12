package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.View.SeriesListFragmentDirections
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.model.Result

class TopRatedSeriesAdapter(val mContext : Context, var seriesList: ArrayList<Result>) : RecyclerView.Adapter<TopRatedSeriesAdapter.topRatedViewHolder>() {

    class topRatedViewHolder (val binding : MovieRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): topRatedViewHolder {
        val binding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return topRatedViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: topRatedViewHolder, position: Int) {
        val series= seriesList[position]
        holder.binding.titleText.text= seriesList[position].name
        holder.binding.yLdZText.text= seriesList[position].voteAverage.toString()


        val url = "https://image.tmdb.org/t/p/w500${seriesList[position].backdropPath}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterView)

        holder.itemView.setOnClickListener {
            val action= SeriesListFragmentDirections.actionSeriesListFragmentToSeriesDetailFragment(series = series)
            Navigation.findNavController(it).navigate(action)
        }
    }
}