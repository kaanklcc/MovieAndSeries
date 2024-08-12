package com.kaankilic.movieapp.model

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Movie(

    @SerializedName("results")
    val results: List<MovieResult>,

    ) {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0

}