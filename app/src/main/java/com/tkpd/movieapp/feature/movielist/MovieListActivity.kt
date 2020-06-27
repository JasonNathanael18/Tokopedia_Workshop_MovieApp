package com.tkpd.movieapp.feature.movielist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tkpd.movieapp.R
import kotlinx.android.synthetic.main.activity_main.*

class MovieListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        supportFragmentManager.beginTransaction()
            .add(R.id.container_view,MovieListFragment())
            .commitNowAllowingStateLoss()
    }
}
