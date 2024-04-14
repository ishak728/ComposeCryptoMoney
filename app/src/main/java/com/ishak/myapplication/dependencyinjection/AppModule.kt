package com.ishak.myapplication.dependencyinjection

import com.google.gson.GsonBuilder
import com.ishak.myapplication.repository.CryptoRepository
import com.ishak.myapplication.repository.CryptoRepositoryInterface
import com.ishak.myapplication.service.retrofit.RetrofitApi
import com.ishak.myapplication.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun retrofitApi():RetrofitApi{
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun cryptoRepositoryInterface(retrofitApi: RetrofitApi):CryptoRepositoryInterface{
        return CryptoRepository(retrofitApi)
    }
}