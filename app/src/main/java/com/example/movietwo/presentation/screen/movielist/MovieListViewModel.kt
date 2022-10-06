package com.example.movietwo.presentation.screen.movielist

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movietwo.common.network.Resource
import com.example.movietwo.data.repository.MovieRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepositoryImpl
) : ViewModel() {

    private val _uiState = MutableStateFlow(MovieUiState())
    val uiState: StateFlow<MovieUiState> = _uiState

    init {
        getMovies()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getMovies() {
        viewModelScope.launch {
            movieRepository.getPopularMoviesUsingSafe().collect{result ->
                when(result.status){
                    Resource.Status.SUCCESS -> {
                        _uiState.value = MovieUiState(status = result.status, movie = result.data!!)
                    }
                    Resource.Status.ERROR -> {
                        _uiState.value =
                            MovieUiState(status = result.status, error = result.message ?: "An unexpected error occured.")
                    }
                    Resource.Status.LOADING -> {
                        _uiState.value = MovieUiState(status = result.status, isLoading = true)
                    }
                }
            }
        }
    }

}