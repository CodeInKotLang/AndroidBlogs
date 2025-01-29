package com.example.androidblogs.data.repository

import com.example.androidblogs.data.local.BlogDao
import com.example.androidblogs.data.mapper.toBlogEntityList
import com.example.androidblogs.data.mapper.toBlogList
import com.example.androidblogs.data.remote.RemoteBlogDataSource
import com.example.androidblogs.domain.model.Blog
import com.example.androidblogs.domain.repository.BlogRepository

class BlogRepositoryImpl(
    private val remoteBlogDataSource: RemoteBlogDataSource,
    private val localBlogDataSource: BlogDao
): BlogRepository {

    override suspend fun getAllBlogs(): List<Blog>? {
        val remoteBlogs = remoteBlogDataSource.getAllBlogs()
        return if (remoteBlogs != null) {
            localBlogDataSource.deleteAllBlogs()
            localBlogDataSource.insertAllBlogs(remoteBlogs.toBlogEntityList())
            remoteBlogs.toBlogList()
        } else {
            val localBlogs = localBlogDataSource.getAllBlogs()
            if (localBlogs.isNotEmpty()) {
                localBlogs.toBlogList()
            } else {
                null
            }
        }
    }
}