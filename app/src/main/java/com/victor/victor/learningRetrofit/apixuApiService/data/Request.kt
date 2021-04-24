package com.victor.victor.learningRetrofit.apixuApiService.data


data class Request(
    val language: String,
    val query: String,
    val type: String,
    val unit: String
)