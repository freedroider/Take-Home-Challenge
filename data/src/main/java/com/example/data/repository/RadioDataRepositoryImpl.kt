package com.example.data.repository

import com.example.data.data_source.remote.RemoteDataSource
import com.example.data.data_source.remote.entity.toRadioData
import com.example.domain.entity.RadioData
import com.example.domain.repository.RadioDataRepository
import javax.inject.Inject

class RadioDataRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : RadioDataRepository {
    override suspend fun getRadioData(url: String): RadioData {
        return remoteDataSource.getRadioData(url = url)
            .toRadioData()
    }
}