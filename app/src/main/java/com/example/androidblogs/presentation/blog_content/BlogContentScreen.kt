package com.example.androidblogs.presentation.blog_content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BlogContentScreen(
    onBackClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        BlogContentTopBar(
            onBackClick = onBackClick
        )
        Text(
            text = "This is a content Screen."
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BlogContentTopBar(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit
) {
    TopAppBar(
        windowInsets = WindowInsets(0),
        modifier = modifier,
        title = { Text(text = "Blog Content") },
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