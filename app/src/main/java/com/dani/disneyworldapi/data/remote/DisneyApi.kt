package com.dani.disneyworldapi.data.remote

import com.dani.disneyworldapi.data.remote.model.ApiResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url
import com.dani.disneyworldapi.data.remote.model.Data
import com.dani.disneyworldapi.data.remote.model.DataDetail
import retrofit2.http.Path

interface DisneyApi {

    // Obtener todos los personajes
    @GET("character?pageSize=7438")
    fun getData(): Call<ApiResponse>

    // Obtener detalles de un personaje específico por ID
    @GET("character/{id}")
    fun getDataDetail(
        @Path("id") id: Int
    ): Call<DataDetail>
}
