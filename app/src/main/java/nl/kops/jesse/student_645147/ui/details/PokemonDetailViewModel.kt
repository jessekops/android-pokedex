package nl.kops.jesse.student_645147.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nl.kops.jesse.student_645147.data.entity.PokemonDetail
import nl.kops.jesse.student_645147.data.repository.PokemonDetailRepository
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val repository: PokemonDetailRepository
) : ViewModel() {

    private val _pokemonDetail = MutableStateFlow<PokemonDetail?>(null)
    val pokemonDetail: StateFlow<PokemonDetail?> = _pokemonDetail

    fun fetchPokemonDetail(id: Int) {
        viewModelScope.launch {
            try {
                val detail = repository.getPokemonDetail(id)
                _pokemonDetail.value = detail
            } catch (e: Exception) {
                _pokemonDetail.value = null
                println("Error fetching Pokémon detail: $e")
            }
        }
    }
}
