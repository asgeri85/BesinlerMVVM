package com.example.besinlermvvmbtk.services

class ApiUtils {
    companion object{
        private const val URL="https://raw.githubusercontent.com/atilsamancioglu/BTK20-JSONVeriSeti/master/"
        fun getBesinAPI():BesinAPI{
            return RetrofitClient.getRetrofit(URL).create(BesinAPI::class.java)
        }
    }
}