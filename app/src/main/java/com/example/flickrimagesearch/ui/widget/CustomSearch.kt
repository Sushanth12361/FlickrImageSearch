package com.example.flickrimagesearch.ui.widget

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.flickrimagesearch.R
import com.example.flickrimagesearch.ui.theme.Gray33
import com.example.flickrimagesearch.ui.theme.RobotoRegular

@Composable
fun CustomSearch(
    hint: String = stringResource(R.string.type_your_search),
    isLoading: Boolean = false,
    onTextChange: (String) -> Unit = {},
    keyboardButtonClick: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val text = rememberSaveable { mutableStateOf("") }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp, end = 16.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = if(isSystemInDarkTheme()) Gray33 else Color.White
        ),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector =
                ImageVector.vectorResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .width(24.dp)
            )
            TextField(
                value = text.value,
                onValueChange = {
                    text.value = it
                    onTextChange.invoke(text.value)
                },
                modifier = Modifier
                    .testTag("search_input")
                    .weight(1f)
                    .padding(end = 8.dp),
                placeholder = {
                    Text(
                        text = hint,
                        color = Color.Gray,
                        fontSize = 16.sp,
                        fontFamily = RobotoRegular,
                        textAlign = TextAlign.Center,
                    )
                },
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = RobotoRegular,
                    fontSize = 16.sp
                ),
                maxLines = 1,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(
                    onAny = {
                        keyboardButtonClick.invoke(text.value)
                        keyboardController?.hide()
                    }),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = Color.Black,
                )
            )
            if(isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(24.dp)
                        .height(24.dp)
                )

                Spacer(modifier = Modifier.width(12.dp))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomSearchPreview() {
    CustomSearch(
        hint = stringResource(R.string.type_your_search)
    )
}