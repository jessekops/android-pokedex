package nl.kops.jesse.student_645147.data.mapper

import nl.kops.jesse.student_645147.data.entity.PokemonDetail
import nl.kops.jesse.student_645147.data.model.PokemonDetailResponse
import javax.inject.Inject

class PokemonDetailMapper @Inject constructor() {
    fun mapPokemonDetail(response: PokemonDetailResponse): PokemonDetail {
        return PokemonDetail(
            id = response.id,
            name = response.name,
            weight = response.weight,
            height = response.height,
            types = response.types.map { it.type.name },
            abilities = response.abilities.map { it.ability.name }
        )
    }
}
