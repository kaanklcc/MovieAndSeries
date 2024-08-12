package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.View.FavouriteFragmentDirections
import com.kaankilic.movieapp.ViewModel.FavouriteFragmentViewModel
import com.kaankilic.movieapp.databinding.FavRecyclerRowBinding
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.model.MovieResult

class FavouriteAdapter(val mContext : Context, var favList : MutableList<MovieResult>,private val viewModel: FavouriteFragmentViewModel) : RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder>() {



    class FavouriteViewHolder(val binding : MovieRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteViewHolder {
        val binding = MovieRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return FavouriteViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return favList.size
    }

    override fun onBindViewHolder(holder: FavouriteViewHolder, position: Int) {
        val movie = favList[position]
        holder.binding.titleText.text= favList[position].originalTitle
        holder.binding.yLdZText.text=favList[position].voteAverage.toString()
        val url = "https://image.tmdb.org/t/p/w500${favList[position].backdropPath}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterView)

        holder.itemView.setOnClickListener {
            val action = FavouriteFragmentDirections.actionFavouriteFragmentToMovieDetailFragment(movie)
            Navigation.findNavController(it).navigate(action)
        }

        holder.binding.smallIcon.setOnClickListener {
            viewModel.deleteMovies(movie)
            favList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, favList.size)
        }
    }
    }
