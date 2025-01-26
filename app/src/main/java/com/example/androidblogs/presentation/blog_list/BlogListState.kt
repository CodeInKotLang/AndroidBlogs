package com.example.androidblogs.presentation.blog_list

import com.example.androidblogs.domain.model.Blog

data class BlogListState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val blogs: List<Blog> = emptyList()
)
