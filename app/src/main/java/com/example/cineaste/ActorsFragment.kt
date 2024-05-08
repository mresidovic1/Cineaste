package com.example.cineaste

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ActorsFragment() : Fragment() {
    private lateinit var actorsRV:RecyclerView
    private var actorsList= listOf<String>()
    private lateinit var actorsRVSimpleAdapter:SimpleStringAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view:View = inflater.inflate(R.layout.fragment_actors, container, false)
        val intent = requireActivity().intent
        val extras = intent.extras

        if (extras != null) {
            if (extras.containsKey("movie_title")) {
                actorsList = getMovieActors()?.get(extras.getString("movie_title"))?: emptyList()
            }
            else if (extras.containsKey("movie_id")){
                getActorsById(extras.getLong("movie_id"))
            }
        }

        actorsRV = view.findViewById<RecyclerView>(R.id.listActors)
        actorsRV.layoutManager = LinearLayoutManager(activity)
        actorsRVSimpleAdapter = SimpleStringAdapter(actorsList)
        actorsRV.adapter = actorsRVSimpleAdapter
        return view
    }
    fun getActorsById(query: Long){
        val scope = CoroutineScope(Job() + Dispatchers.Main)
        scope.launch{
            val result =ActorMovieRepository.getActors(query)
            when (result) {
                is Result.Success<MutableList<String>> -> actorsRetrieved(result.data)
                else-> Log.v("meh","meh")
            }
        }
    }
    fun actorsRetrieved(actors:MutableList<String>){
        actorsList=actors
        actorsRVSimpleAdapter.list = actors;
        actorsRVSimpleAdapter.notifyDataSetChanged();
    }
}