package com.example.rickrolled.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.rickrolled.ui.theme.RickrolledTheme
import com.example.rickrolled.ui.screen.RickrolledApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickrolledTheme {
                RickrolledApp()
            }
        }
    }
}