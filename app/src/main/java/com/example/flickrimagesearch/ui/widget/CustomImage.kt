package com.example.flickrimagesearch.ui.widget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun CustomImage(
    url: String,
    description: String? = null,
    modifier: Modifier = Modifier
) {
    GlideImage(
        model = url,
        contentDescription = description,
        contentScale = ContentScale.Crop,
        modifier = modifier,
    )
}