package com.example.rickrolled.ui.screen.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.rickrolled.utils.SharedPreferencesExtensions

class SettingsScreenViewModel(private val preferences: SharedPreferencesExtensions) : ViewModel() {
    val isDarkThemeEnabled = mutableStateOf(false)

    init {
        isDarkThemeEnabled.value = preferences.get("isDarkThemeEnabled", false) as Boolean
    }

    fun changeTheme() {
        isDarkThemeEnabled.value = isDarkThemeEnabled.value.not()
        preferences.save("isDarkThemeEnabled", isDarkThemeEnabled.value)
    }
}