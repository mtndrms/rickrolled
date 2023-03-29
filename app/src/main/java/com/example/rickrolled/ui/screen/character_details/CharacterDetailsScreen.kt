package com.example.rickrolled.ui.screen.character_details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CharacterDetailsScreen(
    id: Int?, viewModel: CharacterDetailsViewModel = getViewModel(parameters = { parametersOf(id) })
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(25.dp)
    ) {
        Text(text = "ID: ${viewModel.character.value?.id}")
        Text(text = "Name: ${viewModel.character.value?.name}")
        Text(text = "Created: ${viewModel.character.value?.created}")
        Text(text = "Episode Count: ${viewModel.character.value?.episode?.size}")
        Text(text = "Location: ${viewModel.character.value?.location?.name}")
        Text(text = "Status: ${viewModel.character.value?.status}")
        Text(text = "Type: ${viewModel.character.value?.type}")
    }
}