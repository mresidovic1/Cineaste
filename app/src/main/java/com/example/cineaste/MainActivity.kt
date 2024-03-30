package com.example.cineaste

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.widget.EditText
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
    private lateinit var search : EditText
    private val broadcast : BroadcastReceiver = ConnectivityBroadcastReceiver()
    private val filter = IntentFilter("android.net.conn.CONNECTIVITY_CHANGE")


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        favoriteMovies = findViewById(R.id.favoriteMovies)
        upcomingMovies = findViewById(R.id.recentMovies)
        search = findViewById(R.id.searchText)
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
        favoriteMoviesAdapter = MovieListAdapter(arrayListOf()) { movie -> showMovieDetails(movie) }
        upcomingMoviesAdapter = MovieListAdapter(arrayListOf()) { movie -> showMovieDetails(movie) }
        favoriteMovies.adapter = favoriteMoviesAdapter
        upcomingMovies.adapter = upcomingMoviesAdapter
        favoriteMoviesAdapter.updateMovies(favoriteMoviesList)
        upcomingMoviesAdapter.updateMovies(upcomingMoviesList)
        if(intent?.action == Intent.ACTION_SEND && intent?.type == "text/plain")
            handleSendText(intent)
    }

    private fun handleSendText(intent: Intent) {
        intent.getStringExtra(Intent.EXTRA_TEXT)?.let {
            search.setText(it)
        }
    }
    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(this, MovieDetailActivity::class.java).apply {
            putExtra("movie_title", movie.title)
        }
        startActivity(intent)
    }
    override fun onResume() {
        super.onResume()
        registerReceiver(broadcast, filter)
    }
    override fun onPause() {
        unregisterReceiver(broadcast)
        super.onPause()
    }
}