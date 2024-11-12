package nl.kops.jesse.student_645147.data.entity

data class PokemonDetail(
    val id: Int,
    val name: String,
    val weight: Int,
    val height: Int,
    val types: List<String>,
    val abilities: List<String>
)