package com.example.terryschmidt.amiibomania.model

import com.google.gson.annotations.SerializedName

class AmiiboListWrapper {
    @SerializedName("amiibo")
    var amiiboList: List<Amiibo>

    constructor(list: List<Amiibo>) {
        this.amiiboList = list
    }
}