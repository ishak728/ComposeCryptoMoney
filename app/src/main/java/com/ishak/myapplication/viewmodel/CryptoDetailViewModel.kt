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

    var latestPrice = mutableStateOf<List<com.ishak.myapplication.model.latestprice.Response>>(listOf())
    var isLatestPriceError = mutableStateOf(false)
    var isLatestPriceLoading = mutableStateOf(false)



    fun loadImageData(id:String){
        isImageLoading.value=true

        viewModelScope.launch {

            val result=repository.getImage(id)

            when(result){
                is Resource.Success->{

                    val response= result.data?.response?.get(0)?.icon
                    //image="https://fcsapi.com/assets/images/slug/bitcoin.png"
                    if (response != null) {

                        image.value=response
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
        isLatestPriceLoading.value=true

        viewModelScope.launch {
            val result=repository.getLatestPrice(id)
            when(result){
                is Resource.Success->{
                    val response= result.data?.response
                    if (response != null) {
                        latestPrice.value=response
                    }
                    isLatestPriceLoading.value=false
                    isLatestPriceError.value=false
                }
                is Resource.Error->{
                    isLatestPriceError.value=true
                    isLatestPriceLoading.value=false
                }
                is Resource.Loading->{
                    isLatestPriceLoading.value=true
                    isLatestPriceError.value=false
                }
            }

        }
    }





}