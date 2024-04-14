package com.ishak.myapplication.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ishak.myapplication.model.cryptomoneylist.CryptoList
import com.ishak.myapplication.model.cryptomoneylist.Response
import com.ishak.myapplication.repository.CryptoRepositoryInterface
import com.ishak.myapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class CryptoListViewModel @Inject constructor(
    val cryptoRepositoryInterface: CryptoRepositoryInterface
) : ViewModel() {



    var cryptoList= mutableStateOf<List<Response>>(listOf())
    var isError= mutableStateOf<Boolean>(false)
    var isLoading= mutableStateOf(false)


     fun loadData(){
         isLoading.value=true

         runBlocking {
             val new=async {
                 cryptoRepositoryInterface.getCryptoList()
             }

             val result=new.await()

             when(result){
                 is Resource.Success->{
                     cryptoList.value= result.data?.response!!
                     isError.value=false
                     isLoading.value=false
                 }
                 is Resource.Error->{
                     isError.value=true
                 }
                 is Resource.Loading -> {
                     isLoading.value=true

                 }
             }

         }

/*
         var result:Resource<CryptoList>?=null


       val qwer= CoroutineScope(Dispatchers.IO).launch {
           result = cryptoRepositoryInterface.getCryptoList()
       }

         qwer.invokeOnCompletion {
             println("incokeCompettetetettion")
             when(result){
                 is Resource.Success->{
                     cryptoList.value= result!!.data?.response?.slice(0 until 20) ?: listOf()
                     //cryptoList.value= result!!.data?.response?: listOf()

                     isError.value=false
                     isLoading.value=false
                 }

                 is Resource.Error->{
                     isError.value=true


                 }


                 is Resource.Loading -> {
                     isLoading.value=true

                 }

                 null -> TODO()
             }
         }*/




    }


}