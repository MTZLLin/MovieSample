package com.kmh.moviesample.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kmh.moviesample.api.ApiClient
import com.kmh.moviesample.model.NowPlayingModel
import com.kmh.moviesample.model.ResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {

    private var movies: MutableLiveData<NowPlayingModel> = MutableLiveData()
    private var details : MutableLiveData<ResultsItem> = MutableLiveData()

    fun getArticle():MutableLiveData<NowPlayingModel> = movies
    fun getDetaild () : LiveData<ResultsItem> = details


    fun loadData(){
        var apiClient= ApiClient()
        var apiCall=apiClient.getNowPlaying()
        apiCall.enqueue(object : Callback<NowPlayingModel> {
            override fun onFailure(call: Call<NowPlayingModel>, t: Throwable) {
                Log.d("Error>>>",t.toString())
            }

            override fun onResponse(
                call: Call<NowPlayingModel>,
                response: Response<NowPlayingModel>
            ) {
                movies.value=response.body()
            }

        })
    }
    fun loadDetai(id : Int){
        var apiClient = ApiClient()
        var apiCall = apiClient.getDetailMovie(id)

        apiCall.enqueue(
            object : Callback<ResultsItem>{
                override fun onFailure(call: Call<ResultsItem>, t: Throwable) {

                }

                override fun onResponse(call: Call<ResultsItem>, response: Response<ResultsItem>) {
                    details.value = response.body()
                }

            }
        )
    }
}