package com.kmh.moviesample.api


import com.kmh.moviesample.model.NowPlayingModel
import com.kmh.moviesample.model.ResultsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiInterface {

    @GET("now_playing")
    fun getNowPlaying(
        @Query("api_key")api_key:String
    ):Call<NowPlayingModel>
    @GET("{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") movie_id : Int,
        @Query("api_key") api_key : String
    ) : Call<ResultsItem>


}