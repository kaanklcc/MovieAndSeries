package com.kaankilic.movieapp.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kaankilic.film.RoomDB.MovieAndSeriesDB
import com.kaankilic.movieapp.model.CastMember
import com.kaankilic.movieapp.model.MovieResult
import com.kaankilic.movieapp.model.TrailerResult
import com.kaankilic.movieapp.service.MovieAPIServis

import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {


    private val movieApiService = MovieAPIServis()
    val favouriteMovies= MutableLiveData<MovieResult>()
    val movieTrailer = MutableLiveData<TrailerResult>()
    val cast = MutableLiveData<List<CastMember>>()

    fun getCastMembers(movieId: Int) {
        viewModelScope.launch {
            val response = movieApiService.getDataCast(movieId, "****************")
            if (response.isSuccessful) {
                val creditsResponse = response.body()
                if (creditsResponse != null) {
                    cast.value = creditsResponse.cast
                } else {
                    Log.d("MovieDetailViewModel", "Response body is null.")
                }
            } else {
                Log.d("MovieDetailViewModel", "Response is not successful: ${response.code()} - ${response.message()}")
            }
        }
    }

    fun favMovie(movie: MovieResult) {
        viewModelScope.launch {
            val dao = MovieAndSeriesDB(getApplication()).movieDao()
            val existingMovies = dao.getAllMovies()
            val isMovieAlreadyInDb = existingMovies.any { it.id == movie.id }

            if (!isMovieAlreadyInDb) {
                dao.insertMovie(movie)
            }
            favouriteMovies.postValue(movie)
        }
    }

    fun movieTrailer(movieId: Int) {
        viewModelScope.launch {
            val response = movieApiService.getMovieTrailer(movieId,"****************")
            if (response.isSuccessful){
                movieTrailer.value= response.body()?.results?.firstOrNull()
            }
        }

    }








}
