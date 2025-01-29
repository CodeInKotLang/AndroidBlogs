package com.example.androidblogs.data.mapper

import com.example.androidblogs.data.local.entity.BlogEntity
import com.example.androidblogs.domain.model.Blog

fun BlogEntity.toBlog(
    content: String? = null
) = Blog(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contentUrl = contentUrl,
    content = content
)

fun List<BlogEntity>.toBlogList() = map { it.toBlog() }