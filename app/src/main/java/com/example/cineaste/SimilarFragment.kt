package com.example.cineaste

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class SimilarFragment() : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_similar, container, false)
        val intent = requireActivity().intent
        val extras = intent.extras

        var similarList = emptyList<String>()
        if (extras != null) {
            similarList = getSimilarMovies()[extras.getString("movie_title")] ?: emptyList()
        }

        var similarRV = view.findViewById<RecyclerView>(R.id.listSimilar)
        similarRV.layoutManager = LinearLayoutManager(activity)
        var similarRVSimpleAdapter = SimpleStringAdapter(similarList)
        similarRV.adapter = similarRVSimpleAdapter
        return view
    }

}