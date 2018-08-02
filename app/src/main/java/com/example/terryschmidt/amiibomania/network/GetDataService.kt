package com.example.terryschmidt.amiibomania.network

import com.example.terryschmidt.amiibomania.model.AmiiboListWrapper
import retrofit2.Call
import retrofit2.http.GET

interface GetDataService {
    @GET("/api/amiibo/?type=figure")
    fun getAmiiboListWrapper(): Call<AmiiboListWrapper>
}