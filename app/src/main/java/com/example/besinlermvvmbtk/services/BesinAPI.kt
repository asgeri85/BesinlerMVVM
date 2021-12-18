package com.example.besinlermvvmbtk.services

import com.example.besinlermvvmbtk.model.Besin
import io.reactivex.Single
import retrofit2.http.GET

interface BesinAPI {

    @GET("besinler.json")
    fun besinGetir():Single<List<Besin>>
}