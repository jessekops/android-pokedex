package nl.kops.jesse.student_645147.ui.favorites

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.kops.jesse.student_645147.data.entity.Pokemon
import nl.kops.jesse.student_645147.data.repository.PokemonDetailRepository
import nl.kops.jesse.student_645147.data.store.FavoritesDataStore
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val pokemonDetailRepository: PokemonDetailRepository
) : ViewModel() {

    private val favoritesDataStore = FavoritesDataStore(context)

    private val _favoritesList = MutableStateFlow<List<Pokemon>>(emptyList())
    val favoritesList: StateFlow<List<Pokemon>> = _favoritesList

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    init {
        loadFavorites()
    }

    private fun loadFavorites() {
        viewModelScope.launch {
            favoritesDataStore.favoritesFlow.collect { favoriteIds ->
                _loading.value = true
                _favoritesList.value = favoriteIds.mapNotNull { id ->
                    getPokemonById(id)
                }
                _loading.value = false
            }
        }
    }

    private suspend fun getPokemonById(id: String): Pokemon? {
        return try {
            val pokemonDetail = pokemonDetailRepository.getPokemonDetail(id.toInt())
            Pokemon(
                id = pokemonDetail.id,
                name = pokemonDetail.name,
                imageUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${pokemonDetail.id}.png"
            )
        } catch (e: Exception) {
            null
        }
    }
}
