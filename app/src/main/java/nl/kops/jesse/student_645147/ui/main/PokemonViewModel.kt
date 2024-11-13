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

    private var fullPokemonList = listOf<Pokemon>()

    init {
        fetchPokemonList()
    }

    private fun fetchPokemonList() {
        viewModelScope.launch {
            try {
                _loading.value = true
                fullPokemonList = repository.getPokemonList()
                _pokemonList.value = fullPokemonList
            } catch (e: Exception) {
                print(e)
            } finally {
                _loading.value = false
            }
        }
    }

    fun searchPokemon(query: String) {
        viewModelScope.launch {
            _pokemonList.value = if (query.isEmpty()) {
                fullPokemonList
            } else {
                fullPokemonList.filter { it.name.contains(query, ignoreCase = true) }
            }
        }
    }
}
