package com.example.androidblogs.data.mapper

import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.model.Blog

fun BlogDto.toBlog() = Blog(
    id = id,
    title = title,
    thumbnailUrl = thumbnailUrl,
    contentUrl = contentUrl,
    content = null
)

fun List<BlogDto>.toBlogList() = map { it.toBlog() }