package com.dani.disneyworldapi.data.remote

import com.dani.disneyworldapi.data.remote.model.Data
import retrofit2.Call
import retrofit2.http.GET

import com.dani.disneyworldapi.data.remote.model.DataDetail
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface DisneyApi {
    //https://private-afcb0-disney2.apiary-mock.com/characters/characters_list
    //https://private-afcb0-disney2.apiary-mock.com/characters/character_detail/0

    //Primer end point
    @GET
    fun getData(
        @Url url: String?
    ): Call<MutableList<Data>>

    @GET("characters/characters_list")
    fun getDataApiary(): Call<MutableList<Data>>

    //@GET("characters/character_detail")
    //fun getDataDetail(
    //  @Query("id") id: String?
    //): Call<DataDetail>

    //Segundo end point
    @GET("characters/character_detail/{id}")
    fun getDataDetailApiary(
        @Path("id") id: String?
    ): Call<DataDetail>


}
