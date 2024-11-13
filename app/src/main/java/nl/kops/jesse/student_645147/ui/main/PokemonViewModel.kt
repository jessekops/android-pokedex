package nl.kops.jesse.student_645147.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.kops.jesse.student_645147.data.entity.Pokemon
import nl.kops.jesse.student_645147.data.repository.PokemonRepository
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val repository: PokemonRepository
) : ViewModel() {

    private val _pokemonList = MutableStateFlow<List<Pokemon>>(emptyList())
    val pokemonList: StateFlow<List<Pokemon>> = _pokemonList

    private val _loading = MutableStateFlow(true)
    val loading: StateFlow<Boolean> = _loading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private var fullPokemonList = listOf<Pokemon>()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                fullPokemonList = repository.getPokemonList()
                _pokemonList.value = fullPokemonList
            } catch (e: Exception) {
                _error.value = "Failed to load Pokémon. Please check your connection and try again."
            } finally {
                _loading.value = false
            }
        }
    }

    fun retry() {
        fetchPokemonList()
    }

    fun searchPokemon(query: String) {
        _pokemonList.value = if (query.isEmpty()) {
            fullPokemonList
        } else {
            fullPokemonList.filter { it.name.contains(query, ignoreCase = true) }
        }
    }
}

