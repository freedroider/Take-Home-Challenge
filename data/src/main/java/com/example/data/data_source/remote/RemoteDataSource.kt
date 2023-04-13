package com.example.data.data_source.remote

import com.example.data.data_source.remote.entity.RemoteRadioData

interface RemoteDataSource {
    suspend fun getRadioData(url: String) : RemoteRadioData
}