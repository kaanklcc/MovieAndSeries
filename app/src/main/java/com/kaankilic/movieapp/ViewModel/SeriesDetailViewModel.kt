package com.kaankilic.movieapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaankilic.film.RoomDB.MovieAndSeriesDB
import com.kaankilic.movieapp.model.CastMember
import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.model.TrailerResult
import com.kaankilic.movieapp.service.MovieAPIServis

import kotlinx.coroutines.launch

class SeriesDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val movieAPIServis = MovieAPIServis()
    val seriesTrailer = MutableLiveData<TrailerResult>()

    val seriesCast= MutableLiveData<List<CastMember>>()


    fun getSeriesCast(seriesId:Int){
        viewModelScope.launch {
            val response = movieAPIServis.getDataSeriesCast(seriesId,"aaaaf180f736635b6d7b6e158b4b8da0")
            if (response.isSuccessful){
                seriesCast.value= response.body()?.cast
            }else{

            }

        }
    }
    fun favMovie(movie: MovieResult){
        viewModelScope.launch {
            val dao = MovieAndSeriesDB(getApplication()).movieDao()
            dao.insertMovie(movie)
        }
    }

    fun seriesTrailer(movieId: Int) {
        viewModelScope.launch {
            val response = movieAPIServis.getMovieTrailer(movieId, "a07e22bc18f5cb106bfe4cc1f83ad8ed")
            if (response.isSuccessful) {
                seriesTrailer.value = response.body()?.results?.firstOrNull()
            }
        }
    }













}