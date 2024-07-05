package com.example.flickrimagesearch.ui.view.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.data.FlickrItem
import com.example.flickrimagesearch.ui.theme.Typography
import com.example.flickrimagesearch.ui.widget.CustomSearch
import com.example.flickrimagesearch.ui.widget.CustomToolbar

@Composable
fun MainView(
    flickrItems: List<FlickrItem>,
    isLoading: Boolean = false,
    isInitialState: Boolean = true,
    onSearch: (String) -> Unit = {},
    onItemSelected: (FlickrItem) -> Unit = {}
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxHeight()
    ) {
        CustomToolbar()
        CustomSearch(
            isLoading = isLoading,
            keyboardButtonClick = onSearch,
            onTextChange = onSearch
        )
        if(flickrItems.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.padding(all = 12.dp)
            ) {
                items(flickrItems) {flickrItem ->
                    MainItemView(
                        flickrItem = flickrItem,
                        onClick = onItemSelected
                    )
                }
            }
        } else {
            Spacer(modifier = Modifier.height(24.dp))
            Icon(
                imageVector =
                ImageVector.vectorResource(id = R.drawable.ic_empty_search),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(100.dp)
                    .height(100.dp),
                tint = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = if (isInitialState) stringResource(R.string.initial_state)
                else stringResource(R.string.empty_state),
                style = Typography.titleSmall,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainPreview() {
    MainView(
        flickrItems = emptyList()
    )
}