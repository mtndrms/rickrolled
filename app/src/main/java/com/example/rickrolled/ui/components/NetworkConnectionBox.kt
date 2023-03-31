package com.example.rickrolled.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun NetworkConnectionBox(state: MutableTransitionState<Boolean>) {
    AnimatedVisibility(
        visibleState = state,
        enter = expandVertically(animationSpec = tween(2000)),
        exit = shrinkVertically(animationSpec = tween(2000))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .width(40.dp)
                .background(if (!state.targetState) Color.Green else Color.Red),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (!state.targetState) "Back online" else "No network connection!",
                color = Color.White
            )
        }
    }
}