package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.dto.BlogDto
import com.example.androidblogs.domain.util.Result

interface RemoteBlogDataSource {
    suspend fun getAllBlogs(): Result<List<BlogDto>>
    suspend fun fetchBlogContent(url: String): Result<String>
}