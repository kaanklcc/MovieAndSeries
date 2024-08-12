package com.kaankilic.movieapp.model


import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Series(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Result>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0

}