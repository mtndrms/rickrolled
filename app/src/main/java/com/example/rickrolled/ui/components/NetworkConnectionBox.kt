package com.example.rickrolled.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.rickrolled.ui.screen.MainScreenViewModel
import kotlinx.coroutines.delay
import org.koin.androidx.compose.getViewModel

@Composable
fun NetworkConnectionBox(mainScreenViewModel: MainScreenViewModel = getViewModel()) {
    val backgroundColor by animateColorAsState(targetValue = if (mainScreenViewModel.isNetworkAvailable.value) Color.Green else Color.Red)

    AnimatedVisibility(
        visibleState = mainScreenViewModel.state,
        enter = expandVertically(animationSpec = tween(2000)),
        exit = shrinkVertically(animationSpec = tween(2000))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .width(40.dp)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (mainScreenViewModel.isNetworkAvailable.value) "Back online" else "No network connection!",
                color = Color.White
            )
        }
    }
}