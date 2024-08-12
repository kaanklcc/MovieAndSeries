package com.kaankilic.movieapp.service

import com.kaankilic.movieapp.model.CreditsResponse
import com.kaankilic.movieapp.model.Movie
import com.kaankilic.movieapp.model.Series
import com.kaankilic.movieapp.model.Trailer
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieAPI {

    @GET("movie/popular")
    suspend fun getMovie(@Query("api_key") apiKey : String) : Response<Movie>

    @GET("movie/top_rated")
    suspend fun getTopRated(@Query("api_key") apiKey : String) : Response<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcoming(@Query("api_key") apiKey : String) : Response<Movie>

    @GET("tv/on_the_air")
    suspend fun getOnTheAir(@Query("api_key") apiKey : String) : Response<Series>

    @GET("tv/popular")
    suspend fun getPopularSeries(@Query("api_key") apiKey : String) : Response<Series>

    @GET("tv/top_rated")
    suspend fun getTopRatedSeries(@Query("api_key") apiKey : String) : Response<Series>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") movieId: Int, @Query("api_key") apiKey: String): Response<CreditsResponse>

    @GET("tv/{series_id}/aggregate_credits")
    suspend fun getSeriesCredits(@Path("series_id") seriesId: Int, @Query("api_key") apiKey:String): Response<CreditsResponse>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(@Path("movie_id") movieId: Int, @Query("api_key") apiKey : String): Response<Trailer>

    @GET("tv/{series_id}/videos")
    suspend fun getSeriesTrailer(@Path("series_id") seriesId: Int, @Query("api_key") apiKey : String): Response<Trailer>



}