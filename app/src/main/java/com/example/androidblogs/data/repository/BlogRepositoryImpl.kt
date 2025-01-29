package com.example.androidblogs.data.repository

import com.example.androidblogs.data.mapper.toBlogList
import com.example.androidblogs.data.remote.RemoteBlogDataSource
import com.example.androidblogs.domain.model.Blog
import com.example.androidblogs.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource
): BlogRepository {

    override suspend fun getAllBlogs(): List<Blog>? {
        return remoteBlogDataSource.getAllBlogs()?.toBlogList()
    }
}