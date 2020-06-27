package com.tkpd.movieapp.feature.movielist

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.tkpd.movieapp.R
import com.tkpd.movieapp.feature.moviedetail.MovieDetailActivity
import com.tkpd.movieapp.feature.movielist.adapter.MovieAdapter
import com.tkpd.movieapp.feature.movielist.view.MovieListListener
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.util.doSuccessOrFail
import kotlinx.android.synthetic.main.movie_list_fragment.*

class MovieListFragment : Fragment(), MovieListListener {

    companion object {
        fun newInstance() = MovieListFragment()
    }

    private lateinit var viewModel: MovieListViewModel

    //lazy -> dibuat ketika dipanggil
    private val adapterMovieAdapter by lazy { MovieAdapter(this) }

    private val viewModelFactory = MovieListViewModelFactory()
    private val movieViewModel: MovieListViewModel by viewModels(factoryProducer = { viewModelFactory })

    private var movieData: MutableList<MovieItem>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.movie_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        movieViewModel.getMovieList()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieViewModel.topRatedMovies.observe(viewLifecycleOwner, Observer { data ->
            data.doSuccessOrFail({
                movieData = it.data?.movieItems?.toMutableList()
                movieData?.let { it1 -> adapterMovieAdapter.setMovieList(it1) }
            }, {

            })
        })
    }

    private fun initRecyclerView() {
        rv_movie_list?.layoutManager = GridLayoutManager(context, 3)
        rv_movie_list?.adapter = adapterMovieAdapter
    }

    override fun onMovieClicked(movieId: Int) {
        val intent = Intent(context, MovieDetailActivity::class.java).apply {
            putExtra(MovieDetailActivity.MOVIE_ID_EXTRA, movieId)
        }
        startActivity(intent)
    }

}