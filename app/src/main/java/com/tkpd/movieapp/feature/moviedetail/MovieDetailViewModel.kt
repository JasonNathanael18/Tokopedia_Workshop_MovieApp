package com.tkpd.movieapp.feature.moviedetail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tkpd.movieapp.datasource.repository.MovieDetailRepository
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel (private val repository: MovieDetailRepository): ViewModel(){
    private val _movieDetail = MutableLiveData<Result<MovieDetail?>>()
    val movieDetail: LiveData<Result<MovieDetail?>>
    get() = _movieDetail

    init{
        _movieDetail.value = Result.Loading
    }

    fun getMovieDetail(movieId: Int){
        viewModelScope.launch(Dispatchers.IO){
            try {
                val data = repository.getMovieDetailFromAPI(movieId)
                _movieDetail.postValue(data)
            }catch (e: Throwable){
                _movieDetail.postValue(Result.Error(e))
            }
        }
    }
}