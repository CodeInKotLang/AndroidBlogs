package com.example.androidblogs.presentation.blog_content

sealed interface BlogContentAction {
    data object Refresh : BlogContentAction
}