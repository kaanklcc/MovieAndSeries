package com.kaankilic.movieapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaankilic.film.RoomDB.MovieAndSeriesDB

import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.model.Result

import kotlinx.coroutines.launch

class FavouriteFragmentViewModel(application: Application) : AndroidViewModel(application) {



    val favouriteMovies= MutableLiveData<List<MovieResult>>()
    val favouriteSeries= MutableLiveData<Result>()



    fun roomVerisiniAl(uuid: Int) {
        viewModelScope.launch {
            val dao = MovieAndSeriesDB(getApplication()).movieDao()
            val movie = dao.getAllMovies()
            favouriteMovies.value = movie
        }
    }

    fun deleteMovies(movie: MovieResult) {
        viewModelScope.launch {
            val dao = MovieAndSeriesDB(getApplication()).movieDao()
            dao.deleteMovie(movie)
        }
    }




}