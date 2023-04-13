package com.example.common

import timber.log.Timber

object AppLogger {
    init {
        Timber.plant(Timber.DebugTree())
    }

    fun i(message: String) {
        Timber.i(message)
    }
}