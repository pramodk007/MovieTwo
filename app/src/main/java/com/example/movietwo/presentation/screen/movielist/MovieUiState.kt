package com.example.movietwo.presentation.screen.movielist

import com.example.movietwo.common.network.Resource
import com.example.movietwo.domain.model.MovieItemModel

data class MovieUiState(
    val status: Resource.Status = Resource.Status.LOADING,
    val isLoading: Boolean = false,
    val movie: List<MovieItemModel> = emptyList(),
    val error: String = ""
)