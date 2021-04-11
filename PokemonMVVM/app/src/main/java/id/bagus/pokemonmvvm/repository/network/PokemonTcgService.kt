package id.bagus.pokemonmvvm.repository.network

import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.model.PokemonSet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTcgService {
    @GET("cards")
    suspend fun getCards(@Query("set") set: String): Response<PokemonCard.PokemonCardResponse>

    @GET("sets")
    suspend fun getSets(): Response<PokemonSet.PokemonSetResponse>
}