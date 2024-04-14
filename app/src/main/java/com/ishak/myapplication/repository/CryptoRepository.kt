package com.ishak.myapplication.repository

import com.ishak.myapplication.model.cryptomoneylist.CryptoList
import com.ishak.myapplication.model.cryptomoneylist.Info
import com.ishak.myapplication.model.cryptomoneylist.Response
import com.ishak.myapplication.model.image.Image
import com.ishak.myapplication.model.latestprice.LatestPrice
import com.ishak.myapplication.service.retrofit.RetrofitApi
import com.ishak.myapplication.util.Resource
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    val retrofitApi: RetrofitApi
):CryptoRepositoryInterface {
    override suspend fun getCryptoList(): Resource<CryptoList> {
       return try {
          // val result=retrofitApi.getCryptoList()


           var qwelist= (1..20).toList().map {
               Response(it.toString(),it.toString(),"czxcxz",it.toString())
           }


           var asaf=CryptoList(1, Info(12,"asd"),"asdf",qwelist,true)

           Resource.Success(asaf)
       }
       catch (e:Exception){
           Resource.Error("Error! No Data")
       }
    }

    override suspend fun getLatestPrice(id: String): Resource<LatestPrice> {
       val result=try {
            retrofitApi.getLatestPrice(id)
        }catch (e:Exception){
            return Resource.Error("Error! No Data")
        }
        return Resource.Success(result)
    }

    override suspend fun getImage(id: String): Resource<Image> {
        return try {
            val result=retrofitApi.getImage(id)
            println("Crypto respositoryyy:::"+result.response[0].icon)
            Resource.Success(result)
        }catch (e:Exception){
            println("eeeeeeeeeeee"+e)
            println("Eroooooooooooooooooor cryptorepository getiamge func")
            Resource.Error("Error! No Data")

        }
    }
}