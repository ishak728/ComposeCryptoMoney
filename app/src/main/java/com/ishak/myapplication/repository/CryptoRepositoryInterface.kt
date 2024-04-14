package com.ishak.myapplication.repository

import com.ishak.myapplication.model.cryptomoneylist.CryptoList
import com.ishak.myapplication.model.image.Image
import com.ishak.myapplication.model.latestprice.LatestPrice
import com.ishak.myapplication.util.Resource

interface CryptoRepositoryInterface {

    suspend fun getCryptoList():Resource<CryptoList>

    suspend fun getLatestPrice(id: String):Resource<LatestPrice>

    suspend fun getImage(id:String):Resource<Image>
}