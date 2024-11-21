package com.dani.disneyworldapi.data.remote.model

import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("info")
    val info: Info,
    @SerializedName("data")
    val data: List<Data>

)

data class Info(
    @SerializedName("count")
    val count: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("nextPage")
    val nextPage: String?
)