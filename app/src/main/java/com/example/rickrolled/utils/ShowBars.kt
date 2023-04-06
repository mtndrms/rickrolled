package com.example.rickrolled.utils

import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun showBars(flag: Boolean) {
    rememberSystemUiController().apply {
        isNavigationBarVisible = flag
        isStatusBarVisible = flag
        isSystemBarsVisible = flag
    }
}