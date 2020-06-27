package com.tkpd.movieapp.feature.moviedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tkpd.movieapp.datasource.repository.MovieDetailRepositoryImpl
import com.tkpd.movieapp.datasource.repository.MovieListRepositoryImpl
import com.tkpd.movieapp.feature.movielist.MovieListViewModel

class MovieDetailViewModelFactory: ViewModelProvider.Factory{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailViewModel(MovieDetailRepositoryImpl()) as T
    }
}