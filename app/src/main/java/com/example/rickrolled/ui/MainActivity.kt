package com.example.rickrolled.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.example.rickrolled.navigation.NavigationHost
import com.example.rickrolled.ui.screen.settings.SettingsScreenViewModel
import com.example.rickrolled.ui.theme.RickrolledTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import org.koin.androidx.compose.getViewModel

class MainActivity() : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberAnimatedNavController()
            val settingsScreenViewModel: SettingsScreenViewModel = getViewModel()
            val isDarkThemeEnabled by remember { settingsScreenViewModel.isDarkThemeEnabled }

            RickrolledTheme(darkTheme = isDarkThemeEnabled) {
                NavigationHost(navController)
            }
        }
    }
}
