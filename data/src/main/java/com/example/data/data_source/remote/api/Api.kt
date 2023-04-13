package com.example.data.data_source.remote.api

import com.example.data.data_source.remote.entity.RemoteRadioData
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface Api {
    @GET
    suspend fun getRadioData(
        @Url url: String,
        @Query("render") render: String = "json"
    ): RemoteRadioData
}