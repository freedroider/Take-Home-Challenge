package com.example.data.data_source.remote

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RemoteDataSourceModule {
    @Singleton
    @Binds
    fun bindDataSource(dataSource: RemoteDataSourceImpl) : RemoteDataSource
}