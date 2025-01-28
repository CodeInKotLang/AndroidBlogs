package com.example.androidblogs.presentation.blog_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidblogs.data.mapper.toBlogList
import com.example.androidblogs.data.remote.HttpClientFactory
import com.example.androidblogs.data.remote.KtorRemoteBlogDataSource
import io.ktor.client.engine.okhttp.OkHttp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BlogListViewModel: ViewModel() {

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


    private val httpClient = HttpClientFactory.create(OkHttp.create())
    private val remoteDataSource = KtorRemoteBlogDataSource(httpClient)
    private fun getAllBlogs() {
        viewModelScope.launch {
            val blogDtos = remoteDataSource.getAllBlogs()
            if (blogDtos != null) {
                _state.update { it.copy(blogs = blogDtos.toBlogList()) }
            }
        }
    }

}