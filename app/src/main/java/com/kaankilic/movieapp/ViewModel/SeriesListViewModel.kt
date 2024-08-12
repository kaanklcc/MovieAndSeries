package com.kaankilic.movieapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaankilic.movieapp.model.Result
import com.kaankilic.movieapp.model.TrailerResult
import com.kaankilic.movieapp.service.MovieAPIServis
import kotlinx.coroutines.launch

class SeriesListViewModel(application: Application) : AndroidViewModel(application) {

    val popularSeries = MutableLiveData<List<Result>>()
    val topRatedSeries = MutableLiveData<List<Result>>()
    val onTheAirSeries = MutableLiveData<List<Result>>()

    private val movieApiService = MovieAPIServis()

    init {
        getPopularSeries()
        getTopRatedSeries()
        getOnTheAirSeries()
    }

    fun getPopularSeries(){
        viewModelScope.launch {
            try {
                val response = movieApiService.getDataSeriesPopular("aaaaf180f736635b6d7b6e158b4b8da0")
                if (response.isSuccessful){
                    popularSeries.value= response.body()?.results
                }else{

                }

            }catch (e:Exception){

            }
        }

    }


    fun getTopRatedSeries(){
        viewModelScope.launch {
            try {
                val response = movieApiService.getDataSeriesTopRated("aaaaf180f736635b6d7b6e158b4b8da0")
                if (response.isSuccessful){
                    topRatedSeries.value= response.body()?.results
                }else{

                }

            }catch (e:Exception){

            }
        }
    }

    fun getOnTheAirSeries(){
        viewModelScope.launch {
            try {
                val response = movieApiService.getDataSeriesOnTheAir("aaaaf180f736635b6d7b6e158b4b8da0")
                if (response.isSuccessful){
                    onTheAirSeries.value= response.body()?.results

                }else{

                }
            }catch (e:Exception){

            }
        }
    }




}