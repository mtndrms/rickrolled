package com.example.rickrolled.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rickrolled.navigation.character.CharacterNavDirections
import com.example.rickrolled.navigation.episode.EpisodeNavDirections

@Composable
fun TopBar(
    modifier: Modifier,
    navController: NavHostController,
    currentScreen: String,
    canNavigateBack: Boolean,
    toEpisodeList: () -> Unit = {},
    toFavoriteList: () -> Unit = {},
    clearAllFavorites: () -> Unit = {}
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .then(modifier),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = currentScreen,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth()
        )

        if (canNavigateBack) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "go back",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        } else {
            IconButton(
                onClick = toEpisodeList,
                modifier = Modifier.align(Alignment.CenterStart)
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "settings",
                    tint = MaterialTheme.colors.onPrimary
                )
            }
        }

        Row(
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            if (currentScreen != CharacterNavDirections.favorites.route) {
                IconButton(onClick = toFavoriteList) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "favorite",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            } else {
                IconButton(onClick = {
                    clearAllFavorites()
                }) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "clear all",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    }
}