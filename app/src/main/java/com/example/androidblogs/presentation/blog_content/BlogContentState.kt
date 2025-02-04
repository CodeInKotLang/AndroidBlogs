package com.example.androidblogs.presentation.blog_content

import com.example.androidblogs.domain.model.Blog

data class BlogContentState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val blog: Blog? = null
)
