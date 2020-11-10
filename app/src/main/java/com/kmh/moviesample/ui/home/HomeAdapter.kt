package com.kmh.moviesample.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kmh.moviesample.R
import com.kmh.moviesample.model.MovieModel
import com.kmh.moviesample.model.NowPlayingModel
import com.kmh.moviesample.model.ResultsItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_home.view.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){
    private var articlesItems:List<ResultsItem> = ArrayList()
    var clickListener:OnClickListener?=null
    inner class HomeViewHolder(itemView: View):RecyclerView.ViewHolder(itemView),
    View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }

        lateinit var item:ResultsItem

        fun bind(articlesItem:ResultsItem){
            this.item=articlesItem
            itemView.homeTitle.text=articlesItem.originalTitle
            Picasso.get()
                .load("http://image.tmdb.org/t/p/w500"+articlesItem.posterPath)
                .into(itemView.homeImage)
        }

        override fun onClick(v: View?) {
            clickListener?.onClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        var view=LayoutInflater.from(parent.context)
            .inflate(R.layout.item_home,parent,false)
        return HomeViewHolder(view)
    }

    override fun getItemCount(): Int =articlesItems.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(articlesItems[position])
    }

    fun updateArticle(playingList: List<ResultsItem>){
        this.articlesItems=playingList
        notifyDataSetChanged()
    }

    interface OnClickListener{
        fun onClick(item:ResultsItem)
    }
    fun setOnClickListener(clickListener: OnClickListener){
        this.clickListener=clickListener
    }
}