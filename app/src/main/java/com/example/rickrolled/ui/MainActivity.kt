package com.example.rickrolled.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.rickrolled.data.remote.RetrofitClient
import com.example.rickrolled.repository.CharacterRepository
import com.example.rickrolled.ui.screen.character_list.CharacterListScreen
import com.example.rickrolled.ui.screen.character_list.CharacterListViewModel
import com.example.rickrolled.ui.screen.character_list.CharacterListViewModelFactory
import com.example.rickrolled.ui.theme.RickrolledTheme

class MainActivity : ComponentActivity() {
    private val repository = CharacterRepository(RetrofitClient.getInstance())
    private val viewModel by viewModels<CharacterListViewModel> {
        CharacterListViewModelFactory(
            repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickrolledTheme {
                CharacterListScreen(viewModel)
            }
        }
    }
}