package com.example.androidblogs.data.remote

import com.example.androidblogs.data.remote.dto.BlogDto

interface RemoteBlogDataSource {
    suspend fun getAllBlogs(): List<BlogDto>?
}