package com.feature.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.getViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsScreenViewModel = getViewModel()
) {
    val isDarkThemeEnabled by remember { viewModel.isDarkThemeEnabled }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(PaddingValues(16.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Dark theme", color = MaterialTheme.colors.onBackground)
            Switch(
                checked = isDarkThemeEnabled,
                onCheckedChange = {
                    viewModel.changeTheme()
                }
            )
        }
    }
}