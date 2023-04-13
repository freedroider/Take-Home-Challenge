package com.example.presentation.utils

import android.app.Activity
import android.widget.Toast
import androidx.annotation.StringRes

fun Activity.toast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_LONG).show()
}

fun Activity.toast(@StringRes messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_LONG).show()
}

fun Activity.toast(throwable: Throwable) {
    toast(throwable.message ?: "")
}