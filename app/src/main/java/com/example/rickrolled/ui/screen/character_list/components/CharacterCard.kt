package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.rickrolled.data.remote.model.Character

@Composable
fun CharacterCard(
    entry: Character,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = 5.dp,
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                println(entry.id)
            }
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(entry.imageURL)
                    .crossfade(true).crossfade(500)
                    .build(),
                contentDescription = entry.name,
                contentScale = ContentScale.Crop,
                loading = {
                    CircularProgressIndicator(color = Color.Black)
                },
                modifier = Modifier.clip(CircleShape)
            )

            Spacer(
                modifier = Modifier
                    .height(10.dp)
                    .width(1.dp)
            )

            Text(
                text = entry.name,
                fontSize = 18.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp)
            )
        }
    }
}