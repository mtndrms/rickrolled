package com.example.rickrolled.utils

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences

class SharedPreferencesExtensions(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("RickrolledPreferences", MODE_PRIVATE)

    fun save(key: String, value: Any) {
        sharedPreferences.put(key, value)
    }

    fun get(key: String, default: Any): Any? {
        return sharedPreferences.get(key, default)
    }

    fun clear() {
        sharedPreferences.edit().clear().apply()
    }

    fun delete(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }
}

private fun SharedPreferences.put(key: String, value: Any) {
    val editor = edit()

    when (value) {
        is String -> editor.putString(key, value)
        is Boolean -> editor.putBoolean(key, value)
        is Int -> editor.putInt(key, value)
        is Long -> editor.putLong(key, value)
        is Float -> editor.putFloat(key, value)
        is Set<*> -> if (value.all { it is String }) editor.putStringSet(key, value as Set<String>)
    }

    editor.apply()
}

private fun SharedPreferences.get(key: String, default: Any): Any? {
    return when (default) {
        is String -> get(key, default)
        is Boolean -> get(key, default)
        is Int -> get(key, default)
        is Long -> get(key, default)
        is Float -> get(key, default)
        is Set<*> -> if (default.all { it is String }) get(key, default as Set<String>) else default
        else -> default
    }
}

private fun SharedPreferences.get(key: String, default: String): String? {
    return if (contains(key)) getString(key, default) else default
}

private fun SharedPreferences.get(key: String, default: Boolean): Boolean {
    return if (contains(key)) getBoolean(key, default) else default
}

private fun SharedPreferences.get(key: String, default: Int): Int {
    return if (contains(key)) getInt(key, default) else default
}

private fun SharedPreferences.get(key: String, default: Long): Long {
    return if (contains(key)) getLong(key, default) else default
}

private fun SharedPreferences.get(key: String, default: Float): Float {
    return if (contains(key)) getFloat(key, default) else default
}

private fun SharedPreferences.get(key: String, default: Set<String>): Set<String>? {
    return if (contains(key)) getStringSet(key, default) else default
}