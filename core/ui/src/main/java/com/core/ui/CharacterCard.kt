package com.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.core.designsystem.icon.AppIcons
import com.core.network.entity.Character

@Composable
fun CharacterCard(
    character: Character,
    modifier: Modifier = Modifier,
    isFavorite: (id: Int) -> Boolean,
    addFavorite: (id: Int) -> Unit,
    removeFavorite: (id: Int) -> Unit,
    onCardClick: (id: Int) -> Unit,
) {
    var isFavoriteState by remember { mutableStateOf(isFavorite(character.id)) }
    val (noNetworkWarningSnackBarState, setNoNetworkWarningSnackBarState) = remember {
        mutableStateOf(false)
    }

    Card(
        elevation = 5.dp,
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
//                if (isNetworkAvailable) {
                onCardClick(character.id)
                println("id: ${character.id}, next: ${character.nextPage}, prev: ${character.prevPage}")
//                } else {
//                    setNoNetworkWarningSnackBarState(true)
//                }
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
                    color = MaterialTheme.colors.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                )
            }

            Text(
                text = character.id.toString(),
                color = MaterialTheme.colors.onBackground,
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(10.dp),
                fontWeight = FontWeight.Medium,
                fontSize = 20.sp
            )

            IconButton(
                onClick = {
                    if (isFavoriteState) removeFavorite(character.id) else addFavorite(character.id)
                    isFavoriteState = isFavoriteState.not()
                },
                modifier = Modifier.align(Alignment.TopEnd)
            ) {
                if (isFavoriteState) {
                    Icon(
                        imageVector = AppIcons.FavoriteFilled,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.primary
                    )
                } else {
                    Icon(
                        imageVector = AppIcons.FavoriteOutlined,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.primary
                    )
                }
            }

            if (noNetworkWarningSnackBarState) {
                Snackbar(
                    action = {
                        Button(onClick = {
                            setNoNetworkWarningSnackBarState(false)
                        }) {
                            Text("MyAction")
                        }
                    },
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.BottomCenter)
                ) { Text(text = "This is a snackbar!") }
            }
        }
    }
}