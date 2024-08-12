package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.View.SeriesListFragmentDirections
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.databinding.PopularRecyclerViewBinding
import com.kaankilic.movieapp.model.Result

class PopularSeriesAdapter(val mContext : Context, var seriesList : List<Result>) : RecyclerView.Adapter<PopularSeriesAdapter.PopularSeriesViewHolder>() {

    class PopularSeriesViewHolder(val binding : PopularRecyclerViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularSeriesViewHolder {
        val binding = PopularRecyclerViewBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PopularSeriesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return seriesList.size
    }

    override fun onBindViewHolder(holder: PopularSeriesViewHolder, position: Int) {
        val series= seriesList[position]
        holder.binding.titleText.text= seriesList[position].name
        holder.binding.yLdZText.text= seriesList[position].voteAverage.toString()



        val url = "https://image.tmdb.org/t/p/w500${seriesList[position].backdropPath}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterImageView)



        holder.itemView.setOnClickListener {
            val action= SeriesListFragmentDirections.actionSeriesListFragmentToSeriesDetailFragment(series = series)
            Navigation.findNavController(it).navigate(action)
        }
    }
}