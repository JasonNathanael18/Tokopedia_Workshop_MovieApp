package com.tkpd.movieapp.feature.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tkpd.movieapp.R
import com.tkpd.movieapp.feature.movielist.view.MovieListListener
import com.tkpd.movieapp.model.MovieItem
import com.tkpd.movieapp.util.loadImageRounded
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieItemViewHolder(private val view: View): RecyclerView.ViewHolder(view){
    companion object{
        fun inflate(parent: ViewGroup): MovieItemViewHolder{
            return MovieItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list,parent,false)
            )
        }
    }

    fun bind(data: MovieItem, listener: MovieListListener){
        view.setOnClickListener {
            listener.onMovieClicked(data.id)
        }
        with(view){
            movie_title.text = data.title
            movie_image.loadImageRounded(data.posterPath)
        }
    }
}