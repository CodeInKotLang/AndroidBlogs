package com.example.androidblogs.domain.repository

import com.example.androidblogs.domain.model.Blog
import com.example.androidblogs.domain.util.Result

interface BlogRepository {
    suspend fun getAllBlogs(): Result<List<Blog>>
}