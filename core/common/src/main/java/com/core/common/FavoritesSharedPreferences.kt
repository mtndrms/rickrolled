package com.core.common

class FavoritesSharedPreferences(private val preferences: SharedPreferencesExtensions) {
    fun getFavoriteIDs(): Set<String> {
        return preferences.get("favorites", emptySet<String>()) as Set<String>
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

    fun clearFavorites() {
        preferences.remove("favorites")
    }

    fun isFavorite(id: Int): Boolean {
        return getFavoriteIDs().contains(id.toString())
    }
}