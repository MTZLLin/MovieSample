package com.kmh.moviesample.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kmh.moviesample.R
import com.kmh.moviesample.model.ResultsItem
import com.kmh.moviesample.ui.home.HomeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail_movies.*


class DetailMoviesFragment : Fragment() {
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var item : ResultsItem
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movies, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        item = args.item

        var viewmodel : HomeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewmodel.loadDetai(id)

        viewmodel.getDetaild().observe(
            viewLifecycleOwner, Observer {
                movieDetailTitle.text = it.title
                Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500/" + it.posterPath )
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(movieDetailImage)
                voteCount.text  = it.voteCount.toString()
                overviewDetail.text = it.overview
            }
        )
    }

}