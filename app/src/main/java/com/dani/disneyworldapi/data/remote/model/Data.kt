package com.dani.disneyworldapi.data.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serial

data class Data(

    @SerializedName("id")
    var id: String,
    @SerializedName("name")
    var name: String,
    @SerializedName("imageUrl")
    var imageUrl: String,
    @SerializedName("createdAt")
    var createdAt: String
)


