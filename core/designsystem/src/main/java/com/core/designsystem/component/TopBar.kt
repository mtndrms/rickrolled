package com.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.core.designsystem.icon.AppIcons

@Composable
fun TopBar(
    modifier: Modifier,
    topBarState: MutableState<Boolean>,
    navigateUp: () -> Unit,
    currentScreen: String,
    canNavigateBack: Boolean,
    toFavoriteListScreen: () -> Unit = {},
    toSettingsScreen: () -> Unit = {},
    clearAllFavorites: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = topBarState.value,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it }),
        content = {
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
                        onClick = { navigateUp() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = AppIcons.ArrowBack,
                            contentDescription = "go back",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                } else {
                    IconButton(
                        onClick = { toSettingsScreen() },
                        modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Icon(
                            imageVector = AppIcons.Settings,
                            contentDescription = "settings",
                            tint = MaterialTheme.colors.onPrimary
                        )
                    }
                }

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    if (currentScreen != "favorites") {
                        IconButton(onClick = toFavoriteListScreen) {
                            Icon(
                                imageVector = AppIcons.FavoriteFilled,
                                contentDescription = "favorite",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    } else {
                        IconButton(onClick = {
                            clearAllFavorites()
                        }) {
                            Icon(
                                imageVector = AppIcons.Delete,
                                contentDescription = "clear all",
                                tint = MaterialTheme.colors.onPrimary
                            )
                        }
                    }
                }
            }
        }
    )
}