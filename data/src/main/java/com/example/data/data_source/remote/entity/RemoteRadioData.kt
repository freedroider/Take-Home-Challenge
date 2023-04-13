package com.example.data.data_source.remote.entity

import com.example.domain.entity.RadioData
import com.example.domain.entity.RadioElement
import com.example.domain.entity.RadioHead
import com.example.domain.entity.Type
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RemoteRadioData(
    @field:Json(name = "head")
    val head: RemoteRadioHead,
    @field:Json(name = "body")
    val body: List<RemoteRadioElement>
)

fun RemoteRadioData.toRadioData(): RadioData {
    return RadioData(
        head = head.toRadioHead(),
        body = body.map(RemoteRadioElement::toRadioElement)
    )
}

@JsonClass(generateAdapter = true)
data class RemoteRadioHead(
    @field:Json(name = "title")
    val title: String?,
    @field:Json(name = "status")
    val status: String,
)

fun RemoteRadioHead.toRadioHead(): RadioHead {
    return RadioHead(
        title = title,
        status = status,
    )
}

@JsonClass(generateAdapter = true)
data class RemoteRadioElement(
    @field:Json(name = "element")
    val element: String,
    @field:Json(name = "type")
    val type: String?,
    @field:Json(name = "text")
    val text: String,
    @field:Json(name = "URL")
    val url: String?,
    @field:Json(name = "key")
    val key: String?,
    @field:Json(name = "image")
    val image: String?,
    @field:Json(name = "children")
    val children: List<RemoteRadioElement>?
)

fun RemoteRadioElement.toRadioElement(): RadioElement {
    return RadioElement(
        element = element,
        type = Type.fromRaw(type),
        text = text,
        url = url,
        key = key,
        image = image,
        children = children?.map(RemoteRadioElement::toRadioElement)
    )
}