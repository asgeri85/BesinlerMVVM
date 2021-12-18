package com.example.besinlermvvmbtk.services

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        fun getRetrofit(url:String):Retrofit{
            return Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
        }
    }
}