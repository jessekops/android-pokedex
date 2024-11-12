package nl.kops.jesse.student_645147.data.repository

import nl.kops.jesse.student_645147.data.api.PokemonDetailService
import nl.kops.jesse.student_645147.data.mapper.PokemonDetailMapper
import nl.kops.jesse.student_645147.data.entity.PokemonDetail
import javax.inject.Inject

class PokemonDetailRepository @Inject constructor(
    private val service: PokemonDetailService,
    private val mapper: PokemonDetailMapper
) {
    suspend fun getPokemonDetail(id: Int): PokemonDetail {
        val response = service.getPokemonDetail(id)
        return mapper.mapPokemonDetail(response)
    }
}