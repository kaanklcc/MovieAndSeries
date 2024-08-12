package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.databinding.CastRecyclerRowBinding
import com.kaankilic.movieapp.databinding.MovieRecyclerRowBinding
import com.kaankilic.movieapp.model.CastMember

class CastAdapter(val mContext : Context, var memberList : ArrayList<CastMember>) : RecyclerView.Adapter<CastAdapter.CastViewHolder>() {

    class CastViewHolder(val binding : CastRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        val binding = CastRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CastViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return memberList.size
    }

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.binding.titleText.text= memberList[position].name
        holder.binding.yLdZText.text= memberList[position].character
        val url = "https://image.tmdb.org/t/p/w500${memberList[position].profile_path}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterView)
    }
}