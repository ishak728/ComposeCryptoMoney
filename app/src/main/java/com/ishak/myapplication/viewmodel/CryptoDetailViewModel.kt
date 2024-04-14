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




    var image = mutableStateOf("")
    var isImageError = mutableStateOf(false)
    var isImageLoading = mutableStateOf(false)

    var latestPrice by mutableStateOf<List<com.ishak.myapplication.model.latestprice.Response>>(listOf())
    var isLatestPriceError by mutableStateOf(false)
    var isLatestPriceLoading by mutableStateOf(false)



    fun loadImageData(id:String){
        isImageLoading.value=true

        viewModelScope.launch {


            val result=repository.getImage(id)
            println("CryptoDetailViewModel laoadimagedata:::>>>>"+result.data)
            println("image icon::::----"+result.data?.response?.get(0)?.icon)



            when(result){
                is Resource.Success->{

                    val response= result.data?.response?.get(0)?.icon
                    //image="https://fcsapi.com/assets/images/slug/bitcoin.png"
                    if (response != null) {
                        println("succes ::detailviewmodel ---->>>>")
                        image.value=response
                        println("image))))))))))))))))))))))))"+image.value)
                    }
                    isImageLoading.value=false
                    isImageError.value=false
                }
                is Resource.Error->{
                    println("error")
                    isImageError.value=true
                    isImageLoading.value=false
                }
                is Resource.Loading->{
                    println("loading")
                    isImageLoading.value=true
                    isImageError.value=false
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