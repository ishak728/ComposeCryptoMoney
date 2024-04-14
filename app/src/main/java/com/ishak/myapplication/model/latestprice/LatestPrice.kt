package com.ishak.myapplication.model.latestprice

data class LatestPrice(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)