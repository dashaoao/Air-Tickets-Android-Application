package ru.softpeach.airtickets.data.repositories

import ru.softpeach.airtickets.data.sharedPrefs.LastSearchSharedPreferences

class SearchRepository(
    private val lastSearchSharedPreferences: LastSearchSharedPreferences
) {

    fun getLastSearch(): String? {
        return lastSearchSharedPreferences.lastSearch
    }

    fun saveLastSearch(word: String) {
        lastSearchSharedPreferences.lastSearch = word
    }
}
