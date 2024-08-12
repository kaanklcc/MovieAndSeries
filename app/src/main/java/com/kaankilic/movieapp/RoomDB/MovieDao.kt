package com.kaankilic.movieapp.RoomDB

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kaankilic.movieapp.model.FavouriteMovie
import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.model.Result


@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieResult WHERE uuid = :movieId")
    suspend fun getMovie(movieId: Int): MovieResult

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieResult)

    @Query("SELECT * FROM MovieResult")
    suspend fun getAllMovies(): List<MovieResult>

    @Delete
    suspend fun deleteMovie(movieResult: MovieResult)
}

