package com.example.rickrolled.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import com.example.rickrolled.ui.screen.MainScreen
import com.example.rickrolled.ui.theme.RickrolledTheme
import com.feature.settings.SettingsScreenViewModel
import org.koin.androidx.compose.getViewModel

class MainActivity() : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val settingsScreenViewModel: SettingsScreenViewModel = getViewModel()
            val isDarkThemeEnabled by remember { settingsScreenViewModel.isDarkThemeEnabled }

            RickrolledTheme(darkTheme = isDarkThemeEnabled) {
                MainScreen()
            }
        }
    }
}
