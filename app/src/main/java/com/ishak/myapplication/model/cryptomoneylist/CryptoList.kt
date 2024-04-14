package com.ishak.myapplication.model.cryptomoneylist

data class CryptoList(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)