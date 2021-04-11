package id.bagus.pokemonmvvm.repository.network

import id.bagus.pokemonmvvm.model.PokemonSet
import id.bagus.pokemonmvvm.repository.local.PokemonSetDataStore

class PokemonSetRepository private constructor() : BaseRepository<PokemonSetDataStore>() {
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}