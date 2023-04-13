package com.feature.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.common.FavoritesSharedPreferences
import com.core.network.local.AppDatabase
import com.core.network.entity.Character
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val preferences: FavoritesSharedPreferences,
    private val database: AppDatabase
) : ViewModel() {
    var favorites = mutableStateOf<List<Character>>(emptyList())

    init {
        getFavorites()
    }

    fun addFavorite(id: Int) {
        preferences.addFavorite(id)
    }

    fun removeFavorite(id: Int) {
        preferences.removeFavorite(id)
    }

    fun clearFavorites() {
        preferences.clearFavorites()
        favorites.value = emptyList()
    }

    fun isFavorite(id: Int): Boolean {
        return preferences.isFavorite(id)
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favorites.value = database.characterDao()
                .getMultipleCharacter(
                    preferences.getFavoriteIDs().toList()
                )
        }
    }
}