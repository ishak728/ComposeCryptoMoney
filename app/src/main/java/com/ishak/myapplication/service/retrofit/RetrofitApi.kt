package com.ishak.myapplication.service.retrofit

import com.ishak.myapplication.model.cryptomoneylist.CryptoList
import com.ishak.myapplication.model.image.Image
import com.ishak.myapplication.model.latestprice.LatestPrice
import com.ishak.myapplication.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    //list of cryptomoneys
    //https://fcsapi.com/api-v3/forex/list?type=forex&access_key=iTd3Rnduscd6MnqlbPurpN

    //latest price
    //https://fcsapi.com/api-v3/forex/latest?id=1&access_key=iTd3Rnduscd6MnqlbPurpN

    //get image url
    //https://fcsapi.com/api-v3/forex/profile?id=76,77,78,79,80&access_key=iTd3Rnduscd6MnqlbPurpN


    @GET("api-v3/forex/list")
    suspend fun getCryptoList(
        @Query("type") type:String="forex",
        @Query("access_key") access_key:String=API_KEY
    ):CryptoList

    @GET("api-v3/forex/latest")
    suspend fun getLatestPrice(
        @Query("id") id:String,
        @Query("access_key") access_key:String=API_KEY
    ):LatestPrice

    @GET("api-v3/forex/profile")
    suspend fun getImage(
        @Query("id") id:String,
        @Query("access_key") access_key:String=API_KEY
    ):Image



}