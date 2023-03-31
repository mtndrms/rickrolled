package com.example.rickrolled.ui.screen.character_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailsScreen(
    id: Int?, viewModel: CharacterDetailsViewModel = getViewModel(parameters = { parametersOf(id) })
) {
    val character = viewModel.character.value

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character?.image)
                .crossfade(true).crossfade(500)
                .build(),
            contentDescription = character?.name,
            contentScale = ContentScale.Crop,
            loading = {
                CircularProgressIndicator(color = Color.Black)
            },
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${character?.id} - ${character?.name}",
                fontSize = 24.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = MaterialTheme.colors.onSecondary
            )

            IconButton(
                onClick = { },
                modifier = Modifier
                    .clip(
                        CircleShape
                    )
                    .background(MaterialTheme.colors.primary)

            ) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "favorite",
                    tint = MaterialTheme.colors.secondary
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Created: ${character?.created?.substring(0, 10)}", fontSize = 18.sp)
            Text(text = "Episode Count: ${character?.episode?.size}", fontSize = 18.sp)
            Text(text = "Location: ${character?.location?.name}", fontSize = 18.sp)
            Text(text = "Status: ${character?.status}", fontSize = 18.sp)
            Text(text = "Type: ${character?.type}", fontSize = 18.sp)
        }
    }
}