package com.example.androidblogs.presentation.blog_list

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewScreenSizes
import androidx.compose.ui.unit.dp
import com.example.androidblogs.domain.model.Blog
import com.example.androidblogs.presentation.blog_list.component.BlogCard
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@Composable
fun BlogListScreen(
    modifier: Modifier = Modifier,
    state: BlogListState,
    event: Flow<BlogListEvent>
) {

    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {
        event.collect { event ->
            when(event) {
                is BlogListEvent.Error -> {
                    Toast.makeText(context, event.error, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        BlogListTopBar()
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 300.dp),
            contentPadding = PaddingValues(15.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(state.blogs) { blog ->
                BlogCard(
                    blog = blog
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogListTopBar(
    modifier: Modifier = Modifier
) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = { Text(text = "Android Blogs") }
    )
}


@PreviewScreenSizes
@Composable
private fun PreviewBlogListScreen() {
    val dummyList = listOf(
        Blog(
            id = 1,
            title = "State Management in Compose",
            thumbnailUrl = "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiQyCrAWdIb8-moiYuP7EdpznRLOLaKoZWJ04MLzMi1wkJrMfLKQshwXhB_ODNz3T6_aoOwQ0YccVpSbLO2K9qkpx-HTklvNm3ZR_spOINLr861_PgDXDnh6LgpptIyzR5Nv-UjlQ-5FyeLpHwOCb4NjZ8darLIomTVjHM2VvDv7YZdzO-FS6zMKEhlCQ/s1600/Android-JetpackCompose1.2-Social.png",
            contentUrl = "",
            content = null
        )
    )
    BlogListScreen(
        state = BlogListState(blogs = dummyList),
        event = emptyFlow()
    )
}