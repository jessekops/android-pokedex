package nl.kops.jesse.student_645147.data.mapper

import nl.kops.jesse.student_645147.data.entity.Pokemon
import nl.kops.jesse.student_645147.data.model.PokemonListResponse
import javax.inject.Inject

class PokemonListMapper @Inject constructor() {
    fun mapPokemonList(response: PokemonListResponse): List<Pokemon> {
        return response.results.map { pokemonResponse ->
            val id = pokemonResponse.url.split("/").dropLast(1).last().toInt()
            val imageUrl =
                "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/$id.png"

            Pokemon(
                id = id,
                name = pokemonResponse.name,
                imageUrl = imageUrl
            )
        }
    }
}