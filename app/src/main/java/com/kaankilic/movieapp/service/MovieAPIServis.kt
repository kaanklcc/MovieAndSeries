package com.kaankilic.movieapp.service

import com.kaankilic.movieapp.model.CreditsResponse
import com.kaankilic.movieapp.model.Movie
import com.kaankilic.movieapp.model.Series
import com.kaankilic.movieapp.model.Trailer
import retrofit2.Response

class MovieAPIServis {

    private val api = RetrofitInstance.api

    suspend fun getDataPopular(apiKey : String) : Response<Movie> {
        return api.getMovie(apiKey)
    }
    suspend fun getDataRated(apiKey: String) : Response<Movie> {
        return api.getTopRated(apiKey)
    }

    suspend fun getDataUpcoming(apiKey: String) : Response<Movie> {
        return api.getUpcoming(apiKey)
    }

    suspend fun getDataSeriesPopular(apiKey: String) : Response<Series> {
        return api.getPopularSeries(apiKey)

    }

    suspend fun getDataSeriesOnTheAir(apiKey: String) : Response<Series> {
        return api.getOnTheAir(apiKey)
    }

    suspend fun getDataSeriesTopRated(apiKey: String) : Response<Series> {
        return api.getTopRatedSeries(apiKey)
    }

    suspend fun getDataCast(movieid : Int, apiKey: String) : Response<CreditsResponse>{
        return api.getMovieCredits(movieid,apiKey)
    }

    suspend fun getDataSeriesCast(seriesid : Int, apiKey: String) : Response<CreditsResponse>{
        return api.getSeriesCredits(seriesid,apiKey)
    }

    suspend fun getMovieTrailer(movieId : Int, apiKey : String) : Response<Trailer>{
        return api.getMovieTrailer(movieId,apiKey)
    }

    suspend fun getSeriesTrailer(seriesId : Int, apiKey : String) : Response<Trailer>{
        return api.getMovieTrailer(seriesId,apiKey)
    }


}