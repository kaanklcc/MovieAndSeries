package com.kaankilic.movieapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kaankilic.movieapp.databinding.CastRecyclerRowBinding
import com.kaankilic.movieapp.model.CastMember

class SeriesCastAdapter (val mContext: Context, var castList: ArrayList<CastMember>) : RecyclerView.Adapter<SeriesCastAdapter.castViewHolder>() {

    class castViewHolder(val binding: CastRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): castViewHolder {
        val binding = CastRecyclerRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return castViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return castList.size
    }

    override fun onBindViewHolder(holder: castViewHolder, position: Int) {
        holder.binding.titleText.text=castList[position].name
        holder.binding.yLdZText.text= castList[position].character
        val url = "https://image.tmdb.org/t/p/w500${castList[position].profile_path}"
        Glide.with(mContext).load(url).override(342,512).into(holder.binding.posterView)
    }
}