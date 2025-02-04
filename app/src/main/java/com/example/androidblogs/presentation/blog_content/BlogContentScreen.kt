package com.example.androidblogs.presentation.blog_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androidblogs.presentation.common_component.ShimmerEffect
import dev.jeziellago.compose.markdowntext.MarkdownText

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogContentScreen(
    state: BlogContentState,
    onBackClick: () -> Unit,
    onAction: (BlogContentAction) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        BlogContentTopBar(
            onBackClick = onBackClick,
            title = state.blog?.title,
            scrollBehavior = scrollBehavior
        )
        if (state.isLoading) {
            LoadingContent(
                modifier = Modifier.fillMaxSize()
            )
        } else {
            when {
                state.errorMessage != null -> {
                    ErrorContent(
                        modifier = Modifier.fillMaxSize(),
                        errorMessage = state.errorMessage,
                        onRefreshClick = { onAction(BlogContentAction.Refresh) }
                    )
                }

                else -> {
                    MainContent(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(15.dp)
                            .verticalScroll(rememberScrollState()),
                        blogContent = state.blog?.content
                    )
                }
            }
        }
    }
}

@Composable
private fun MainContent(
    modifier: Modifier = Modifier,
    blogContent: String?
) {
    Column(
        modifier = modifier
    ) {
        MarkdownText(
            markdown = blogContent ?: "",
            linkColor = MaterialTheme.colorScheme.secondary,
            isTextSelectable = true,
            syntaxHighlightColor = MaterialTheme.colorScheme.surfaceVariant,
            syntaxHighlightTextColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogContentTopBar(
    modifier: Modifier = Modifier,
    title: String?,
    scrollBehavior: TopAppBarScrollBehavior,
    onBackClick: () -> Unit
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = {
            Text(
                text = title ?: "Blog Content",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Navigate Back"
                )
            }
        }
    )
}

@Composable
private fun ErrorContent(
    modifier: Modifier = Modifier,
    errorMessage: String,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onRefreshClick
        ) {
            Icon(
                modifier = Modifier.size(100.dp),
                imageVector = Icons.Default.Refresh,
                contentDescription = "Refresh"
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = errorMessage,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.error
        )
    }
}

@Composable
private fun LoadingContent(
    modifier: Modifier = Modifier,
    shimmerBackgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            ShimmerEffect(
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .background(shimmerBackgroundColor)
            )
            Column(
                modifier = Modifier.weight(1f)
            ) {
                ShimmerEffect(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth()
                        .height(20.dp)
                        .background(shimmerBackgroundColor)
                )
                Spacer(modifier = Modifier.height(10.dp))
                ShimmerEffect(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .fillMaxWidth(fraction = 0.5f)
                        .height(20.dp)
                        .background(shimmerBackgroundColor)
                )
            }
        }
        repeat(times = 15) {
            ShimmerEffect(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(shimmerBackgroundColor)
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PreviewLoadingContent() {
    LoadingContent()
}