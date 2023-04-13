package com.example.domain.repository

import com.example.domain.entity.RadioData

interface RadioDataRepository {
    suspend fun getRadioData(url: String) : RadioData
}