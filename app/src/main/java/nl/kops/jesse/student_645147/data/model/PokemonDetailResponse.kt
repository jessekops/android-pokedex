package nl.kops.jesse.student_645147.data.model

data class PokemonDetailResponse(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<Type>,
    val abilities: List<Ability>
)

data class Type(
    val type: TypeDetails
)

data class TypeDetails(
    val name: String
)

data class Ability(
    val ability: AbilityDetails
)

data class AbilityDetails(
    val name: String
)
