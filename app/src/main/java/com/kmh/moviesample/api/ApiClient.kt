package com.kmh.moviesample.api


import com.kmh.moviesample.model.MovieModel
import com.kmh.moviesample.model.NowPlayingModel
import com.kmh.moviesample.model.ResultsItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    private val BASE_URL= "https://api.themoviedb.org/3/movie/"
    private val apiInterface:MovieApiInterface
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiInterface=retrofit.create(MovieApiInterface::class.java)
    }

    fun getNowPlaying(): Call<NowPlayingModel> {
        return apiInterface.getNowPlaying("f3a978a7e675e75b7d3b2838545eeed5")

    }
    fun getDetailMovie(id : Int) : Call<ResultsItem>{
        return apiInterface.getDetailMovie(id,"f3a978a7e675e75b7d3b2838545eeed5")
    }


}