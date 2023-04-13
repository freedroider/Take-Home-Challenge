package com.example.data.repository

import com.example.domain.repository.RadioDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {
    @Binds
    fun bindRadioDataRepository(repository: RadioDataRepositoryImpl): RadioDataRepository
}