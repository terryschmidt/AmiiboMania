package com.example.terryschmidt.amiibomania.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {
        @JvmStatic
        private var retrofit: Retrofit? = null
        @JvmStatic
        private val baseUrl = "http://www.amiiboapi.com/"

        @JvmStatic
        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = retrofit2.Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
            }
            return retrofit
        }
    }
}