package com.example.data.data_source.remote

import com.example.data.data_source.remote.api.Api
import com.example.data.data_source.remote.entity.RemoteRadioData
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val api: Api) : RemoteDataSource {
    override suspend fun getRadioData(url: String): RemoteRadioData {
        return api.getRadioData(url = url)
    }
}