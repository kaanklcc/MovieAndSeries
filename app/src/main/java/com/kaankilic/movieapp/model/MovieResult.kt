package com.kaankilic.movieapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable
@Entity
data class MovieResult(


    @ColumnInfo(name= "backdrop_path")
    @SerializedName("backdrop_path")
    val backdropPath: String,

    @ColumnInfo(name= "id")
    @SerializedName("id")
    val id: Int,

    @ColumnInfo(name= "original_language")
    @SerializedName("original_language")
    val originalLanguage: String,

    @ColumnInfo(name= "original_title")
    @SerializedName("original_title")
    val originalTitle: String,

    @ColumnInfo(name= "overview")
    @SerializedName("overview")
    val overview: String,

    @ColumnInfo(name= "popularity")
    @SerializedName("popularity")
    val popularity: Double,

    @ColumnInfo(name= "poster_path")
    @SerializedName("poster_path")
    val posterPath: String,

    @ColumnInfo(name= "release_date")
    @SerializedName("release_date")
    val releaseDate: String,

    @ColumnInfo(name= "vote_average")
    @SerializedName("vote_average")
    val voteAverage: Double,

    @ColumnInfo("isFavourite")
    var isFavourite: Boolean = false

) : Serializable {
    @PrimaryKey(autoGenerate = true)
    var uuid : Int =0

}