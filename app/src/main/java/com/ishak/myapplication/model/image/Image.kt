package com.ishak.myapplication.model.image

data class Image(
    val code: Int,
    val info: Info,
    val msg: String,
    val response: List<Response>,
    val status: Boolean
)