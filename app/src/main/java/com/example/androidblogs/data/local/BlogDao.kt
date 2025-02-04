package com.example.androidblogs.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.androidblogs.data.local.entity.BlogContentEntity
import com.example.androidblogs.data.local.entity.BlogEntity

@Dao
interface BlogDao {

    @Query("SELECT * FROM blogs")
    suspend fun getAllBlogs(): List<BlogEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllBlogs(blogs: List<BlogEntity>)

    @Query("SELECT * FROM blogs WHERE id = :blogId")
    suspend fun getBlogById(blogId: Int): BlogEntity?

    @Query("DELETE FROM blogs")
    suspend fun deleteAllBlogs()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBlogContent(content: BlogContentEntity)

    @Query("SELECT * FROM blog_content WHERE blogId = :blogId")
    suspend fun getBlogContent(blogId: Int): BlogContentEntity?
}