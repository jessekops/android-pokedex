package nl.kops.jesse.student_645147.data.store

import androidx.datastore.preferences.core.edit
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "favorites")

class FavoritesDataStore(private val context: Context) {

    private val favoritesKey = stringSetPreferencesKey("favorites")

    suspend fun addToFavorites(pokemonId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritesKey] ?: emptySet()
            preferences[favoritesKey] = currentFavorites + pokemonId.toString()
        }
    }

    suspend fun removeFromFavorites(pokemonId: Int) {
        context.dataStore.edit { preferences ->
            val currentFavorites = preferences[favoritesKey] ?: emptySet()
            preferences[favoritesKey] = currentFavorites - pokemonId.toString()
        }
    }

    val favoritesFlow: Flow<Set<String>> = context.dataStore.data
        .map { preferences ->
            preferences[favoritesKey] ?: emptySet()
        }
}
