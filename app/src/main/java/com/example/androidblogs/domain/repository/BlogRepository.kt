package com.example.androidblogs.domain.repository

import com.example.androidblogs.domain.model.Blog

interface BlogRepository {
    suspend fun getAllBlogs(): List<Blog>?
}