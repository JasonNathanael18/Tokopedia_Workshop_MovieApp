package com.tkpd.movieapp.feature.movielist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.datasource.repository.MovieListRepository
import com.tkpd.movieapp.model.PopularMovies
import com.tkpd.movieapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieListRepository: MovieListRepository) : ViewModel() {

    private val _topRatedMovies = MutableLiveData<Result<PopularMovies?>>()
    val topRatedMovies: LiveData<Result<PopularMovies?>>
        get() = _topRatedMovies

    init{
        _topRatedMovies.value = Result.Loading
    }

    fun getMovieList(){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val data = movieListRepository.getMovieListFromAPI()
                _topRatedMovies.postValue(data)
            }catch (e: Throwable){
                _topRatedMovies.postValue(Result.Error(e))
            }
        }
    }
}