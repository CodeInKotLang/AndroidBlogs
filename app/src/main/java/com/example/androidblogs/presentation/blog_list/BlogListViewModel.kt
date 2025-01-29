package com.example.androidblogs.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidblogs.domain.repository.BlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogListViewModel(
    private val blogRepository: BlogRepository
): ViewModel() {

    private val _state = MutableStateFlow(BlogListState())
    val state = _state
        .onStart {
            getAllBlogs()
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = _state.value
        )


    private fun getAllBlogs() {
        viewModelScope.launch {
            val blogs = blogRepository.getAllBlogs()
            if (blogs != null) {
                _state.update { it.copy(blogs = blogs) }
            }
        }
    }

}