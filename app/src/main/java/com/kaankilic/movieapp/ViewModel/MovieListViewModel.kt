package com.kaankilic.movieapp.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.service.MovieAPIServis
import kotlinx.coroutines.launch

class MovieListViewModel(application: Application) : AndroidViewModel(application) {

    private val movieApiService = MovieAPIServis()

    val popularMovies = MutableLiveData<List<MovieResult>>()
    val topRatedMovies = MutableLiveData<List<MovieResult>>()
    val upcomingMovies = MutableLiveData<List<MovieResult>>()
    val loading = MutableLiveData<Boolean>()

    init {
        getPopularMovies()
        getTopRatedMovies()
        getUpcomingMovies()

    }


     fun getPopularMovies(){
        viewModelScope.launch {
            try {
                val response = movieApiService.getDataPopular("***************")
                if (response.isSuccessful){
                    popularMovies.value= response.body()?.results
                }else{

                }
            }catch (e:Exception){

            }
        }
    }

    fun getTopRatedMovies(){
        viewModelScope.launch {
            try {

                val response = movieApiService.getDataRated("***************")
                if (response.isSuccessful){
                    topRatedMovies.value= response.body()?.results

                }else{

                }

            }catch (e:Exception){

            }
        }
    }

    fun getUpcomingMovies(){
        viewModelScope.launch {

            try {

                val response = movieApiService.getDataUpcoming("***************")
                if (response.isSuccessful){
                    upcomingMovies.value= response.body()?.results

                }else{

                }

            }catch (e:Exception){

            }
        }
    }

}
