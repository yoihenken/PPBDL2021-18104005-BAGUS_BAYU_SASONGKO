package id.bagus.pokemonmvvm.repository.network

import id.bagus.pokemonmvvm.model.PokemonCard
import id.bagus.pokemonmvvm.repository.local.PokemonCardDataStore

class PokemonCardRepository private constructor() : BaseRepository<PokemonCardDataStore>() {
    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = localDataStore?.getPokemons(set)
        if (cache != null) return cache
        val response = remoteDataStore?.getPokemons(set)
        localDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}