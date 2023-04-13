package com.example.domain.entity

data class RadioData(
    val head: RadioHead,
    val body: List<RadioElement>
)

data class RadioHead(
    val title: String?,
    val status: String,
)

data class RadioElement(
    val element: String,
    val type: Type,
    val text: String,
    val url: String?,
    val key: String?,
    val image: String?,
    val children: List<RadioElement>?
)

sealed class Type(open val rawType: String?) {
    object Undefined : Type(null)
    object Audio : Type("audio")
    object Link : Type("link")
    data class Unsupported(override val rawType: String?) : Type(rawType)

    companion object {
        fun fromRaw(rawType: String?): Type {
            return when (rawType) {
                null -> Undefined
                Audio.rawType -> Audio
                Link.rawType -> Link
                else -> Unsupported(rawType)
            }
        }
    }
}