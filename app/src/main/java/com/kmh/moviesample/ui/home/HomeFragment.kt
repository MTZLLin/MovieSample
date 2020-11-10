package com.kmh.moviesample.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.kmh.moviesample.R
import com.kmh.moviesample.model.MovieModel
import com.kmh.moviesample.model.NowPlayingModel
import com.kmh.moviesample.model.ResultsItem
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeAdapter.OnClickListener {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        var homeAdapter = HomeAdapter()

        homeRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = homeAdapter
        }

        homeAdapter.setOnClickListener(this)

        homeViewModel.getArticle().observe(
            viewLifecycleOwner, Observer { movies ->
                homeAdapter.updateArticle(movies.results as List<ResultsItem>)
            }
        )
    }

    override fun onClick(item: ResultsItem) {

    }

    override fun onResume() {
        super.onResume()
        homeViewModel.loadData()
    }


}