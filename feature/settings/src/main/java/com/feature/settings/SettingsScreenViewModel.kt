package com.feature.settings

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.core.common.SharedPreferencesExtensions

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