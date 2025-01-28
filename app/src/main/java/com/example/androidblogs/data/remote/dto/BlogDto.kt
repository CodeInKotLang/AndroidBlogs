package com.example.androidblogs.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlogDto(
    val id: Int,
    val title: String,
    @SerialName("thumbnail_url")
    val thumbnailUrl: String,
    @SerialName("content_url")
    val contentUrl: String
)
