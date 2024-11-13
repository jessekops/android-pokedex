package nl.kops.jesse.student_645147.ui.details

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.kops.jesse.student_645147.data.entity.PokemonDetail
import nl.kops.jesse.student_645147.data.repository.PokemonDetailRepository
import nl.kops.jesse.student_645147.data.store.FavoritesDataStore
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonDetailRepository,
    @ApplicationContext private val context: Context
) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private val favoritesDataStore = FavoritesDataStore(context)

    fun fetchPokemonDetail(id: Int) {
        viewModelScope.launch {
            try {
                val detail = repository.getPokemonDetail(id)
                _pokemonDetail.value = detail
                _errorMessage.value = null
            } catch (e: Exception) {
                _pokemonDetail.value = null
                _errorMessage.value = "Failed to load Pokémon details. Please try again."
                println("Error fetching Pokémon detail: $e")
            }
        }
    }

    fun retryFetchingPokemonDetail(id: Int) {
        fetchPokemonDetail(id)
    }

    fun addToFavorites(pokemonId: Int) {
        viewModelScope.launch {
            favoritesDataStore.addToFavorites(pokemonId)
        }
    }

    fun removeFromFavorites(pokemonId: Int) {
        viewModelScope.launch {
            favoritesDataStore.removeFromFavorites(pokemonId)
        }
    }

    val favoritesFlow = favoritesDataStore.favoritesFlow
}
