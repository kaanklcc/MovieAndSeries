package com.kaankilic.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
@Entity
data class FavouriteMovie(
    @ColumnInfo(name= "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name= "original_title")
    @SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name= "release_date")
    @SerializedName("release_date")
    val releaseDate: String,

){
    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0

}
