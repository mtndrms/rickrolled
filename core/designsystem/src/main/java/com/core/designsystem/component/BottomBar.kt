package com.core.designsystem.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(
    modifier: Modifier,
    bottomBarState: MutableState<Boolean>,
    toCharactersScreen: () -> Unit = {},
    toEpisodesScreen: () -> Unit = {},
    toLocationsScreen: () -> Unit = {}
) {
    AnimatedVisibility(
        visible = bottomBarState.value,
        enter = slideInVertically(initialOffsetY = { it }),
        exit = slideOutVertically(targetOffsetY = { it }),
        content = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .then(modifier),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = toCharactersScreen, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "characters tab",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                        .background(Color.Gray)
                )

                IconButton(onClick = toEpisodesScreen, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Outlined.List,
                        contentDescription = "episodes tab",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }

                Spacer(
                    modifier = Modifier
                        .height(40.dp)
                        .width(1.dp)
                        .background(Color.Gray)
                )

                IconButton(onClick = toLocationsScreen, modifier = Modifier.weight(1f)) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = "locations tab",
                        tint = MaterialTheme.colors.onPrimary
                    )
                }
            }
        }
    )
}