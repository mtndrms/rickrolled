package com.example.rickrolled.ui.screen.favorites

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickrolled.data.entity.Character
import com.example.rickrolled.data.local.AppDatabase
import com.example.rickrolled.utils.SharedPreferencesExtensions
import kotlinx.coroutines.launch

class FavoritesViewModel(
    private val preferences: SharedPreferencesExtensions,
    private val database: AppDatabase
) : ViewModel() {
    var favorites = mutableStateOf<List<Character>>(emptyList())

    init {
        getFavorites()
    }

    fun addFavorite(id: Int) {
        val favorites = getFavoriteIDs().toMutableSet()
        favorites.add(id.toString())

        preferences.save("favorites", favorites)
    }

    fun removeFavorite(id: Int) {
        val favorites = getFavoriteIDs().toMutableSet()
        favorites.remove(id.toString())

        preferences.save("favorites", favorites)
    }

    private fun getFavoriteIDs(): Set<String> {
        return preferences.get("favorites", emptySet<String>()) as Set<String>
    }

    fun clearFavorites() {
        preferences.clear()
        favorites.value = emptyList()
    }

    fun isFavorite(id: Int): Boolean {
        return getFavoriteIDs().contains(id.toString())
    }

    private fun getFavorites() {
        viewModelScope.launch {
            favorites.value = database.characterDao()
                .getMultipleCharacter(
                    getFavoriteIDs().toList()
                )
        }
    }
}