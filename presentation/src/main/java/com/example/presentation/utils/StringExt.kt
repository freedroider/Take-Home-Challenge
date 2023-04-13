package com.example.presentation.utils

import java.net.URLDecoder
import java.net.URLEncoder

val String.decodeURL: String
    get() = URLDecoder.decode(this, "utf-8")

val String.encodeURL: String
    get() = URLEncoder.encode(this, "utf-8")