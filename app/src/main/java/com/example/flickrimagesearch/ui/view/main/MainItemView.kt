package com.example.flickrimagesearch.ui.view.main

import android.view.View
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.data.FlickrMedia
import com.example.flickrimagesearch.ui.widget.CustomImage

@Composable
fun MainItemView(
    flickrItem: FlickrItem,
    view: View = LocalView.current,
    onClick: (FlickrItem) -> Unit
) {
    Row {
        Column(
            modifier = Modifier
                .weight(1f)
                .aspectRatio(1f)
                .clickable(onClick = {
                    onClick.invoke(flickrItem)
                })
        ) {
            CustomImage(
                url = flickrItem.media.url,
                description = flickrItem.description,
                modifier = Modifier.fillMaxWidth()
                    .wrapContentHeight()
                    .clip(RoundedCornerShape(16.dp)).graphicsLayer {
                    ViewCompat.setTransitionName(view, "imageTransition")
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GuestItemPreview() {
    MainItemView(
        flickrItem = FlickrItem(
            title = "",
            link = "",
            media = FlickrMedia(url = ""),
            description = "",
            author = "",
            published = "",
            tags = "",
        ),
        onClick = {}
    )
}