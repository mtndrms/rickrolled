package com.example.rickrolled.ui.screen.episode_list.components

import android.annotation.SuppressLint
import androidx.compose.animation.core.*
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rickrolled.data.entity.Episode

@SuppressLint("UnusedTransitionTargetStateParameter")
@Composable
fun EpisodeCard(episode: Episode, expand: () -> Unit = {}) {
    var isExpanded by remember { mutableStateOf(false) }

    Card(
        elevation = 5.dp,
        border = BorderStroke(1.dp, Color.LightGray),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { isExpanded = isExpanded.not() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = episode.name, fontSize = 20.sp)
                Text(text = episode.episode)
            }

            IconButton(onClick = expand, modifier = Modifier.align(Alignment.CenterEnd)) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = "more"
                )
            }
        }
    }
    Spacer(modifier = Modifier.height(16.dp))
}