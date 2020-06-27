package com.tkpd.movieapp.feature.movielist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.datasource.repository.MovieListRepositoryImpl

class MovieListViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieListViewModel(MovieListRepositoryImpl()) as T
    }
}