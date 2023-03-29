package com.example.rickrolled.ui.screen.character_list.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.*
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
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.ui.screen.favorites.FavoritesViewModel

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    viewModel: FavoritesViewModel,
    onCardClick: () -> Unit = {}
) {
    var isFavorite by remember { mutableStateOf(viewModel.isFavorite(character.id)) }

    Card(
        elevation = 5.dp,
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                onCardClick()
                println("id: ${character.id}, next: ${character.nextPage}, prev: ${character.prevPage}")
            }
    ) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colors.surface)
                .fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.align(Alignment.Center)
            ) {
                SubcomposeAsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(character.imageURL)
                        .crossfade(true).crossfade(500)
                        .build(),
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    loading = {
                        CircularProgressIndicator(color = Color.Black)
                    },
                    modifier = Modifier
                        .clip(CircleShape)
                )

                Spacer(
                    modifier = Modifier
                        .height(10.dp)
                        .width(1.dp)
                )

                Text(
                    text = character.name,
                    fontSize = 18.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }

            IconButton(
                onClick = {
                    if (!isFavorite) viewModel.addFavorite(character.id) else viewModel.removeFavorite(
                        character.id
                    )
                    isFavorite = isFavorite.not()
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                if (!isFavorite) {
                    Icon(
                        imageVector = Icons.Filled.FavoriteBorder,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.primary
                    )
                } else {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }
        }
    }
}