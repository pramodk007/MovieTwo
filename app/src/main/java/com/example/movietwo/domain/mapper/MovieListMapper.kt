package com.example.movietwo.domain.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movietwo.common.uitls.Constants
import com.example.movietwo.data.remote.response.MovieListResponse
import com.example.movietwo.domain.model.MovieItemModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class MovieListMapper @Inject constructor() : Mapper<MovieListResponse, List<MovieItemModel>> {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun map(dataIn: MovieListResponse): List<MovieItemModel> {
        return dataIn.results.orEmpty().map {
            MovieItemModel(
                it.id,
                it.title,
                it.releaseDate.orEmpty().asReadableDate(),
                it.backdropPath.orEmpty().asBackdropUrl(),
                it.posterPath.orEmpty().asPosterUrl(),
                it.overview.orEmpty(),
                it.popularity,
                it.voteAverage,
                it.voteCount
            )
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun String.asReadableDate(): String {
        return try {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val localDate = LocalDate.parse(this, formatter)
            val newFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")
            newFormatter.format(localDate)
        } catch (e: Exception) {
            ""
        }
    }

    private fun String.asPosterUrl() =
        if (this.isEmpty()) Constants.Remote.DEFAULT_POSTER_URL
        else Constants.Remote.IMG_BASE_URL + "w342" + this

    private fun String.asBackdropUrl() =
        if (this.isEmpty()) Constants.Remote.DEFAULT_BACKDROP_URL
        else Constants.Remote.IMG_BASE_URL + "w780" + this
}