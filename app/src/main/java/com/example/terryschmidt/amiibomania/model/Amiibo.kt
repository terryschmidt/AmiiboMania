package com.example.terryschmidt.amiibomania.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class Amiibo(name: String, amiiboSeries: String, release: JsonObject) {
    @SerializedName("amiiboSeries")
    val amiiboSeries: String
    @SerializedName("name")
    val name: String
    @SerializedName("release")
    val release: JsonObject

    init {
        this.name = name
        this.amiiboSeries = amiiboSeries
        this.release = release
    }
}