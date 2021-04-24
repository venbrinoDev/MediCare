package com.victor.victor.learningRetrofit.Retrofit.api

import com.victor.victor.learningRetrofit.Retrofit.model.Post
import retrofit2.Response
import retrofit2.http.GET

interface SimpleApi {

    @GET("posts/1")
    suspend fun getPost():Response<Post>
}