package com.ishak.myapplication.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishak.myapplication.model.image.Image
import com.ishak.myapplication.model.image.Response
import com.ishak.myapplication.repository.CryptoRepository
import com.ishak.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CryptoDetailViewModel @Inject constructor(
    val repository: CryptoRepository
): ViewModel() {




    var image by mutableStateOf("")
    var isImageError by mutableStateOf(false)
    var isImageLoading by mutableStateOf(false)

    var latestPrice by mutableStateOf<List<com.ishak.myapplication.model.latestprice.Response>>(listOf())
    var isLatestPriceError by mutableStateOf(false)
    var isLatestPriceLoading by mutableStateOf(false)



    fun loadImageData(id:String){
        isImageLoading=true

        viewModelScope.launch {

            println("loadimagedata::::::>>>>>>")
            val result=repository.getImage(id)



            when(result){
                is Resource.Success->{

                    val response= result.data?.response?.get(0)?.icon
                    //image="https://fcsapi.com/assets/images/slug/bitcoin.png"
                    if (response != null) {
                        println("detailviewmodel ---->>>>")
                        image=response
                    }
                    isImageLoading=false
                    isImageError=false
                }
                is Resource.Error->{
                    println("error")
                    isImageError=true
                    isImageLoading=false
                }
                is Resource.Loading->{
                    println("loading")
                    isImageLoading=true
                    isImageError=false
                }
            }

        }

    }

    fun loadLatestPriceData(id: String){
        isLatestPriceLoading=true

        viewModelScope.launch {
            val result=repository.getLatestPrice(id)
            when(result){
                is Resource.Success->{
                    val response= result.data?.response
                    if (response != null) {
                        latestPrice=response
                    }
                    isLatestPriceLoading=false
                    isLatestPriceError=false
                }
                is Resource.Error->{
                    isLatestPriceError=true
                    isLatestPriceLoading=false
                }
                is Resource.Loading->{
                    isLatestPriceLoading=true
                    isLatestPriceError=false
                }
            }

        }
    }





}