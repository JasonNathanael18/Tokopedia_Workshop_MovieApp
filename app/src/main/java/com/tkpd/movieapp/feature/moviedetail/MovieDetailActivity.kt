package com.tkpd.movieapp.feature.moviedetail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tkpd.movieapp.R
import com.tkpd.movieapp.model.MovieDetail
import com.tkpd.movieapp.util.doSuccessOrFail
import com.tkpd.movieapp.util.loadImage
import com.tkpd.movieapp.util.loadImageRounded
import kotlinx.android.synthetic.main.activity_movie_detail.*

/**
 * Created by Yehezkiel on 29/05/20
 */

class MovieDetailActivity : AppCompatActivity() {

    companion object{
        const val MOVIE_ID_EXTRA = "movieId"
    }

    private val mViewModelFactory = MovieDetailViewModelFactory()
    private val viewModelDetail: MovieDetailViewModel by viewModels(factoryProducer = {mViewModelFactory})

    private var movieId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        movieId = intent.getIntExtra(MOVIE_ID_EXTRA,0)
        observeLiveData()
        viewModelDetail.getMovieDetail(movieId)
    }

    private fun observeLiveData() {
        viewModelDetail.movieDetail.observe(this, Observer { data ->
            data.doSuccessOrFail({
                it.data?.let { it1 -> setupView(it1) }
            }, {

            })
        })
    }

    private fun setupView(data: MovieDetail) {
        img_banner.loadImage(data.backdropPath)
        img_movie.loadImageRounded(data.posterPath)
        movie_detail_title.text = data.title
        movie_rating.text = data.voteAverage.toString()
        movie_popularity.text = data.popularity.toString()
        movie_release_date.text = data.releaseDate
        movie_description.text = data.overview
    }
}
