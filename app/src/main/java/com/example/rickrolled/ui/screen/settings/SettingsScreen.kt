package com.example.rickrolled.ui.screen.settings

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.rickrolled.ui.theme.RickrolledTheme
import kotlinx.coroutines.launch
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