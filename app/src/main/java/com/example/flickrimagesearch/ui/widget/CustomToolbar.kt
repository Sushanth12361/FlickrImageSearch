package com.example.flickrimagesearch.ui.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.ui.theme.Typography

@Composable
fun CustomToolbar(
    title: String = stringResource(id = R.string.app_name),
    leftIcon: Int? = null,
    rightIcon: Int? = null,
    leftIconClick: () -> Unit = {},
    rightIconClick: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.background)
            .height(56.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(leftIcon != null) {
            IconButton(onClick = leftIconClick) {
                Image(
                    imageVector = ImageVector.vectorResource(id = leftIcon),
                    contentDescription = "Back",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
        }
        Text(
            text = title,
            style = Typography.titleLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (leftIcon != null) 16.dp else 64.dp,
                    end = if (rightIcon != null) 16.dp else 64.dp
                )
        )
        if(rightIcon != null) {
            IconButton(onClick = rightIconClick) {
                Image(
                    imageVector = ImageVector.vectorResource(id = rightIcon),
                    contentDescription = "Share",
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomToolbarPreview() {
    CustomToolbar()
}