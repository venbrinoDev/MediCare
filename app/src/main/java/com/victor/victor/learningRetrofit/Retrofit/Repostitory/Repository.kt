package com.victor.victor.learningRetrofit.Retrofit.Repostitory

import com.victor.victor.learningRetrofit.Retrofit.api.RetrofitInstance
import com.victor.victor.learningRetrofit.Retrofit.model.Post
import retrofit2.Response

class Repository {


    suspend fun getPost(): Response<Post> {
        return RetrofitInstance.api.getPost()
    }
}