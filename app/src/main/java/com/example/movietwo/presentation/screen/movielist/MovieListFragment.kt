package com.example.movietwo.presentation.screen.movielist

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movietwo.R
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private val viewModel: MovieListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect{  uiState ->
                when{
                    uiState.movie.isNotEmpty() ->{
                        Timber.d("success")
                    }
                    uiState.isLoading ->{
                        Timber.d("loading")
                    }
                    uiState.error.isNotEmpty() ->{
                        Timber.d("error")
                    }
                }
            }
        }
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

}