package com.cirql.vahan

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BuilderRetrofit {
//    val url="https://cirqle-backend.onrender.com/api/v1/";
    val url="http://192.168.4.1/";

    //create okhttp client
    private val okHttpClient = OkHttpClient.Builder()

    // retrofit builder
    private val builder = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).client(
        okHttpClient.build())

    private val retrofit= builder.build();

    fun <T> builService(serviceType: Class<T>) : T{
        return retrofit.create(serviceType)
    }
}