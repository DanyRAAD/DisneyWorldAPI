package com.dani.disneyworldapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class DataDetail(
    @SerializedName("name")
    var name: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("films")
    var films: List<String>
)
