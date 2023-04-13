package com.example.domain.core

interface UseCase<A, R> {
    suspend operator fun invoke(arguments: A): R
}