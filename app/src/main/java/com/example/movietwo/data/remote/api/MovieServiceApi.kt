package com.example.movietwo.data.remote.api

import com.example.movietwo.common.uitls.Constants
import com.example.movietwo.data.remote.response.MovieListResponse
import retrofit2.Response
import retrofit2.http.GET

interface MovieServiceApi {
    @GET("movie/popular")
    suspend fun getPopularMovies(): Response<MovieListResponse>
}