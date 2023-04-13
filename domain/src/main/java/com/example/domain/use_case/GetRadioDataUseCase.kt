package com.example.domain.use_case

import com.example.domain.core.UseCase
import com.example.domain.entity.RadioData
import com.example.domain.repository.RadioDataRepository
import javax.inject.Inject

class GetRadioDataUseCase @Inject constructor(private val repository: RadioDataRepository) : UseCase<String, RadioData> {
    override suspend fun invoke(arguments: String): RadioData {
        return repository.getRadioData(url = arguments)
    }
}