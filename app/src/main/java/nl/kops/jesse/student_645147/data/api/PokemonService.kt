package nl.kops.jesse.student_645147.data.api

import nl.kops.jesse.student_645147.data.model.PokemonListResponse
import retrofit2.http.GET

interface PokemonService {
    @GET("pokemon")
    suspend fun getPokemonList(): PokemonListResponse
}