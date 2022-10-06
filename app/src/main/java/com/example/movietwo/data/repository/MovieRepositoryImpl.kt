package com.example.movietwo.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movietwo.common.network.Resource
import com.example.movietwo.common.network.handleApiResponse
import com.example.movietwo.common.network.safeApiRequest
import com.example.movietwo.data.remote.api.MovieServiceApi
import com.example.movietwo.domain.mapper.MovieListMapper
import com.example.movietwo.domain.model.MovieItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieServiceApi: MovieServiceApi,
    private val movieListMapper: MovieListMapper
) {
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun getPopularMoviesUsingSafe(): Flow<Resource<List<MovieItemModel>>> {
        val networkBoundFlow = safeApiRequest { movieServiceApi.getPopularMovies() }.map {
            when (it.status) {
                Resource.Status.LOADING -> {
                    Resource.loading(null)
                }
                Resource.Status.SUCCESS -> {
                    val result = movieListMapper.map(it.data!!)
                    Resource.success(result)
                }
                Resource.Status.ERROR -> {
                    Resource.error(it.message!!, null)
                }
            }
        }
        return networkBoundFlow.flowOn(Dispatchers.IO)
    }
}
