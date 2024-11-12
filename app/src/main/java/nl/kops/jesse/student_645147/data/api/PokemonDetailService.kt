package nl.kops.jesse.student_645147.data.api

import nl.kops.jesse.student_645147.data.model.PokemonDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonDetailService {
    @GET("pokemon/{id}")
    suspend fun getPokemonDetail(@Path("id") id: Int): PokemonDetailResponse
}
