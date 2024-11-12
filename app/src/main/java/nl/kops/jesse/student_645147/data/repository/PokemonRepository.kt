package nl.kops.jesse.student_645147.data.repository

import nl.kops.jesse.student_645147.data.api.PokemonService
import nl.kops.jesse.student_645147.data.entity.Pokemon
import nl.kops.jesse.student_645147.data.mapper.PokemonListMapper
import nl.kops.jesse.student_645147.data.model.PokemonListResponse
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val service: PokemonService,
    private val mapper: PokemonListMapper
) {
    suspend fun getPokemonList(): List<Pokemon> {
        val response: PokemonListResponse = service.getPokemonList()
        return mapper.mapPokemonList(response)
    }
}