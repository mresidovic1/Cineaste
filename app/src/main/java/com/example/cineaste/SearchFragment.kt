package com.example.cineaste

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {
    private lateinit var searchText: EditText
    private lateinit var searchButton: AppCompatImageButton
    private lateinit var searchMovies: RecyclerView
    private lateinit var searchMoviesAdapter: MovieListAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        searchText = view.findViewById(R.id.searchText)
        arguments?.getString("search")?.let {
            searchText.setText(it)
        }
        searchMovies = view.findViewById(R.id.searchList)
        searchButton = view.findViewById(R.id.searchButton)
        searchMovies.layoutManager = GridLayoutManager(activity, 2)
        searchMoviesAdapter = MovieListAdapter(arrayListOf()) { movie -> showMovieDetails(movie) }
        searchMovies.adapter=searchMoviesAdapter
        searchButton.setOnClickListener{
            onClick();
        }
        return view;
    }
    private fun onClick() {
        val toast = Toast.makeText(context, "Search start", Toast.LENGTH_SHORT)
        toast.show()
        search(searchText.text.toString())
    }
    fun searchDone(movies:List<Movie>){
        val toast = Toast.makeText(context, "Search done", Toast.LENGTH_SHORT)
        toast.show()
        searchMoviesAdapter.updateMovies(movies)
    }
    fun onError() {
        val toast = Toast.makeText(context, "Search error", Toast.LENGTH_SHORT)
        toast.show()
    }
    fun search(query: String){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result = MovieRepository.searchRequest(query)
            when (result) {
                is Result.Success<List<Movie>> -> searchDone(result.data)
                else-> onError()
            }
        }
    }
    private fun showMovieDetails(movie: Movie) {
        val intent = Intent(activity, MovieDetailActivity::class.java).apply {
            putExtra("movie_id", movie.id)
        }
        startActivity(intent)
    }


}