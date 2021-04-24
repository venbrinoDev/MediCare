package com.victor.victor.learningRetrofit.apixuApiService.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.victor.victor.learningRetrofit.apixuApiService.Util.Constant.API_KEY
import com.victor.victor.learningRetrofit.apixuApiService.data.CurrentWeatherResponse
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApixuDataInteface {

    @GET("current")
    fun getCurrentWeather(@Query("query")location:String):Deferred<CurrentWeatherResponse>

    companion object{
        operator fun invoke():ApixuDataInteface{
            val requestInterceptor = Interceptor { chain ->
                val url = chain.request().url()
                    .newBuilder().addQueryParameter("access_key",API_KEY).build();
                val request = chain.request().newBuilder().url(url).build();
                return@Interceptor chain.proceed(request)
            }


            val okHttpClient = OkHttpClient.Builder().addInterceptor(requestInterceptor).build();
            return Retrofit.Builder().client(okHttpClient).baseUrl("https://api.weatherstack.com/")
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create()).build()
                .create(ApixuDataInteface::class.java)
        }
    }

}