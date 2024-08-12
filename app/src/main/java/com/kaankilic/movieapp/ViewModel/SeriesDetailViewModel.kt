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
            val response = movieAPIServis.getDataSeriesCast(seriesId,"****************")
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
            val response = movieAPIServis.getMovieTrailer(movieId, "****************")
            if (response.isSuccessful) {
                seriesTrailer.value = response.body()?.results?.firstOrNull()
            }
        }
    }













}
