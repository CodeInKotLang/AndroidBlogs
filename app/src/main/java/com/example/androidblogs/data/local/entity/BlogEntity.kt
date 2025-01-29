package com.example.androidblogs.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.androidblogs.data.util.Constant.BLOG_TABLE_NAME

@Entity(tableName = BLOG_TABLE_NAME)
data class BlogEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val thumbnailUrl: String,
    val contentUrl: String
)
