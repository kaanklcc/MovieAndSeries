package com.kaankilic.film.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kaankilic.movieapp.RoomDB.MovieDao
import com.kaankilic.movieapp.model.MovieResult

@Database(entities = [MovieResult::class], version = 1)
abstract class MovieAndSeriesDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao


    companion object {
        @Volatile
        private var instance: MovieAndSeriesDB? = null

        private val lock = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(lock) {
            instance ?: databaseOlustur(context).also {
                instance = it
            }
        }

        private fun databaseOlustur(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            MovieAndSeriesDB::class.java,
            "FoodDatabase"

        )
            .build()


    }
}