package id.bagus.pokemonmvvm.repository.local

import id.bagus.pokemonmvvm.model.PokemonSet
import id.bagus.pokemonmvvm.repository.local.PokemonSetDataStore
import id.bagus.pokemonmvvm.repository.network.PokemonTcgService

class PokemonSetRemoteDataStore(private val pokemonTcgService: PokemonTcgService) :
    PokemonSetDataStore {
    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcgService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("Terjadi kesalahan saat melakukan request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}