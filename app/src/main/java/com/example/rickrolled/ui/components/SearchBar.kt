package com.example.rickrolled.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp

@Composable
fun SearchBar(modifier: Modifier, onSearch: (String) -> Unit = {}) {
    var state by remember { mutableStateOf("") }
    var isHintEnabled by remember { mutableStateOf(true) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .shadow(5.dp)
            .then(modifier),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = state,
            onValueChange = {
                state = it
                onSearch(it)
            },
            singleLine = true,
            maxLines = 1,
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .clip(RoundedCornerShape(10.dp))
                .alpha(0.5f)
                .background(MaterialTheme.colors.background)
                .onFocusChanged {
                    isHintEnabled = !it.isFocused
                }
                .padding(vertical = 14.dp)
        )

        if (isHintEnabled) {
            Text(
                text = "Type here...",
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        if (state.isNotEmpty()) {
            IconButton(
                onClick = { state = "" },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Clear,
                    contentDescription = "clear",
                    tint = MaterialTheme.colors.surface
                )
            }
        } else {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(horizontal = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = MaterialTheme.colors.surface
                )
            }
        }
    }
}