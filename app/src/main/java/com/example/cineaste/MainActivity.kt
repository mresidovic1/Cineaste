package com.example.cineaste

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var favoriteMovies: RecyclerView
    private lateinit var upcomingMovies : RecyclerView
    private lateinit var favoriteMoviesAdapter: MovieListAdapter
    private lateinit var upcomingMoviesAdapter : MovieListAdapter
    private var favoriteMoviesList =  getFavoriteMovies()
    private var upcomingMoviesList = getRecentMovies()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        favoriteMovies = findViewById(R.id.favoriteMovies)
        upcomingMovies = findViewById(R.id.recentMovies)
        favoriteMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        upcomingMovies.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        favoriteMoviesAdapter = MovieListAdapter(listOf())
        upcomingMoviesAdapter = MovieListAdapter(listOf())
        favoriteMovies.adapter = favoriteMoviesAdapter
        upcomingMovies.adapter = upcomingMoviesAdapter
        favoriteMoviesAdapter.updateMovies(favoriteMoviesList)
        upcomingMoviesAdapter.updateMovies(upcomingMoviesList)
    }

}